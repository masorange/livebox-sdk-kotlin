package es.masorange.livebox.sdk.domain.livebox.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * The details of a Wi-Fi interface and its access points, as returned by
 * [LiveboxSdk.getWlanInterface][es.masorange.livebox.sdk.LiveboxSdk.getWlanInterface].
 *
 * @param id The interface identifier (e.g., `"24GHz"`), used as `wlanIfc` in other requests.
 * @param status The status of the interface.
 * @param frequency The frequency band of the interface.
 * @param lastChangeTime The time of the last status change, as reported by the router, if available.
 * @param lastChange The time elapsed since the last status change, as reported by the router, if available.
 * @param accessPoints The access points of the interface.
 */
data class WlanInterface(
    @Json(name = "Id") val id: String,
    @Json(name = "Status") val status: Status,
    @Json(name = "Frequency") val frequency: Frequency,
    @Json(name = "LastChangeTime") val lastChangeTime: Int? = null,
    @Json(name = "LastChange") val lastChange: Int? = null,
    @Json(name = "AccessPoints") val accessPoints: List<ShortAccessPoint>,
) {
    /** The status of a Wi-Fi interface or access point. */
    enum class Status(val value: String) {
        /** Enabled. */
        @Json(name = "Up") UP("Up"),
        /** Disabled. */
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

    /**
     * A summary of an access point of a Wi-Fi interface.
     *
     * Use [LiveboxSdk.getAccessPoint][es.masorange.livebox.sdk.LiveboxSdk.getAccessPoint] with [idx] to get its full configuration.
     *
     * @param idx The identifier of the access point, used as `wlanAp` in other requests. Depending on the manufacturer,
     * routers identify access points by an index or by the BSSID without colons. If the router sends no index, it is the
     * [bssid] without colons.
     * @param bssid The BSSID (MAC address) of the access point.
     * @param status The status of the access point.
     * @param remainingDuration The remaining duration reported by the router, if available.
     * @param ssid The network name (SSID).
     */
    @JsonClass(generateAdapter = true)
    data class ShortAccessPoint(
        @Json(name = "Idx") val idx: String,
        @Json(name = "BSSID") val bssid: String,
        @Json(name = "Status") val status: Status,
        @Json(name = "RemainingDuration") val remainingDuration: Int? = null,
        @Json(name = "SSID") val ssid: String
    )

    /** The raw JSON form of [ShortAccessPoint], where the index may be missing. Used only for decoding. */
    @JsonClass(generateAdapter = true)
    data class ShortAccessPointJson(
        @Json(name = "Idx") val idx: String?,
        @Json(name = "BSSID") val bssid: String,
        @Json(name = "Status") val status: Status,
        @Json(name = "RemainingDuration") val remainingDuration: Int? = null,
        @Json(name = "SSID") val ssid: String
    )
}