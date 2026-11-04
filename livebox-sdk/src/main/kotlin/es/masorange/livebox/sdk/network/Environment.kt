package es.masorange.livebox.sdk.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import es.masorange.livebox.sdk.exceptions.NoWifiException
import timber.log.Timber

/**
 * Available environments for the Router.
 */
enum class Environment {
    /** Fake responses for testing, served by a local mock server at `http://localhost:3001` (e.g., Mockoon). */
    TEST,

    /** The real router, reached through the default gateway of the current Wi-Fi network (`http://192.168.1.1` if it can't be detected). */
    LIVE;

    /** The base URL of the router API for this environment. */
    val baseUrl: String
        get() = when (this) {
            TEST -> testBaseUrl
            LIVE -> liveBaseUrl
        }

    companion object {
        private var testBaseUrl: String = "http://localhost:3001" // This points at your Mockoon
        private var liveBaseUrl: String = "http://192.168.1.1"

        /**
         * Sets the [LIVE] base URL to the default gateway of the current network. Called by [es.masorange.livebox.sdk.LiveboxSdkBuilder.build].
         *
         * @throws NoWifiException If the device is not connected to a Wi-Fi network.
         */
        fun setupGateway(context: Context) {
            liveBaseUrl = "http://${getGatewayIp(context)}"
        }

        private fun getGatewayIp(context: Context): String {
            val defaultGateway = "192.168.1.1"

            val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val activeNetwork = connectivityManager.activeNetwork

            val networkCapabilities = connectivityManager.getNetworkCapabilities(activeNetwork)
            val isWifi = networkCapabilities?.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) == true
            if (!isWifi) throw NoWifiException()

            val linkProperties = connectivityManager.getLinkProperties(activeNetwork) ?: return defaultGateway

            val gateway = linkProperties.routes
                .firstOrNull { it.isDefaultRoute && it.gateway is java.net.Inet4Address }
                ?.gateway?.hostAddress

            Timber.d("Gateway IP: $gateway")

            return gateway ?: defaultGateway
        }
    }
}
