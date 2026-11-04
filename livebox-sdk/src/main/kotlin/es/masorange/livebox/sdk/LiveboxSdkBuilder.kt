package es.masorange.livebox.sdk

import android.content.Context
import es.masorange.livebox.sdk.network.Environment

/**
 * Builder for instantiating [LiveboxSdk].
 *
 * ```kotlin
 * val liveboxSdk = LiveboxSdkBuilder()
 *     .withContext(context)
 *     .withEnvironment(Environment.LIVE)
 *     .build()
 * ```
 */
class LiveboxSdkBuilder {
    private var context: Context? = null
    private var environment: Environment? = null

    /** Sets the [Context] where the init is performed. Required. */
    fun withContext(context: Context) = apply { this.context = context }

    /** Sets the [Environment] to use. Required. */
    fun withEnvironment(environment: Environment) = apply { this.environment = environment }

    /**
     * Build the [LiveboxSdk] with the provided [Context] and [Environment].
     *
     * With [Environment.LIVE], the router IP is read from the default gateway of the current Wi-Fi network.
     *
     * @return the [LiveboxSdk] instance.
     * @throws IllegalArgumentException If the context or the environment are not set.
     * @throws es.masorange.livebox.sdk.exceptions.NoWifiException If the environment is [Environment.LIVE] and the device is not connected to a Wi-Fi network.
     */
    fun build(): LiveboxSdk {
        requireNotNull(context)
        requireNotNull(environment)
        return LiveboxSdk(context!!, environment!!)
            .apply { init() }
    }
}