package es.masorange.livebox.sdk.domain.livebox.models

import com.squareup.moshi.Json
import es.masorange.livebox.sdk.network.adapters.FlexibleBoolean

/**
 * An entry of the list returned by [LiveboxSdk.getWifiInterfaces][es.masorange.livebox.sdk.LiveboxSdk.getWifiInterfaces].
 *
 * The list mixes the Wi-Fi interfaces with an entry for the Wi-Fi status button, which reflects the general Wi-Fi
 * ON/OFF switch of the router web interface:
 * ```json
 * [
 *   { "WiFiStatusButton": "True" },
 *   { "Id": "24GHz", "Status": "Up", "Frequency": "2.4GHz" },
 *   { "Id": "5GHz", "Status": "Up", "Frequency": "5GHz" }
 * ]
 * ```
 * Both kinds of entries use this model, so every property is nullable. Keep the entries with an [id] to get the interfaces:
 * ```kotlin
 * val interfaces = liveboxSdk.getWifiInterfaces().filter { it.id != null }
 * ```
 *
 * @param id The interface identifier (e.g., `"24GHz"`), used as `wlanIfc` in other requests. `null` for the status button entry.
 * @param wifiStatusButton Whether the router Wi-Fi is enabled through the general ON/OFF switch. `null` for interface entries.
 * Some routers send it as a string (`"True"`/`"False"`), which is also accepted.
 * @param status The status of the interface. `null` for the status button entry.
 * @param frequency The frequency band of the interface. `null` for the status button entry.
 */
data class Wifi(
    @Json(name = "Id") val id: String? = null,
    @FlexibleBoolean @Json(name = "WiFiStatusButton") val wifiStatusButton: Boolean? = null,
    @Json(name = "Status") val status: Status? = null,
    @Json(name = "Frequency") val frequency: Frequency? = null
) {
    /** The status of a Wi-Fi interface. */
    enum class Status(val value: String) {
        /** The interface is enabled. */
        @Json(name = "Up") UP("Up"),
        /** The interface is disabled. */
        @Json(name = "Down") DOWN("Down")
    }

    /** The frequency band of a Wi-Fi interface. */
    enum class Frequency(val value: String) {
        /** The 2.4 GHz band. */
        @Json(name = "2.4GHz") _24GHZ("2.4GHz"),
        /** The 5 GHz band. */
        @Json(name = "5GHz") _5GHZ("5GHz"),
        /** The 6 GHz band. */
        @Json(name = "6GHz") _6GHZ("6GHz")
    }
}