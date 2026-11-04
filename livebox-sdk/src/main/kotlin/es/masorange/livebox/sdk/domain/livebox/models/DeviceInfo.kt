package es.masorange.livebox.sdk.domain.livebox.models

import com.squareup.moshi.Json

/**
 * Represents the information of a device connected to the Livebox router.
 *
 * Use [LiveboxSdk.getDeviceDetail][es.masorange.livebox.sdk.LiveboxSdk.getDeviceDetail] with [physAddress] to get all its details.
 *
 * @param ipAddress The IP address of the device, if available.
 * @param physAddress The physical (MAC) address of the device.
 * @param hostName The name of the device, which the user will see.
 * @param interfaceType The type of interface used by the device (e.g., Ethernet, Wi-Fi).
 * @param active Indicates whether the device is currently active.
 * @param alias The alias or user-defined name for the device.
 * @param ipV6Address The IPv6 address of the device, if available.
 */
data class DeviceInfo(
    @Json(name = "ipAddress") val ipAddress: String?,
    @Json(name = "physAddress") val physAddress: String,
    @Json(name = "hostName") val hostName: String,
    @Json(name = "interfaceType") val interfaceType: InterfaceType,
    @Json(name = "active") val active: Boolean,
    @Json(name = "alias") val alias: String,
    @Json(name = "ipV6Address") val ipV6Address: String?
) {
    /** The interface a device is connected through. `Wifi24`, `Wifi50` and `Wifi60` are the 2.4, 5 and 6 GHz bands. */
    enum class InterfaceType(val value: String) {
        @Json(name = "Ethernet") ETHERNET("Ethernet"),
        @Json(name = "Wifi") WIFI("Wifi"),
        @Json(name = "Wifi24") WIFI24("Wifi24"),
        @Json(name = "Wifi50") WIFI50("Wifi50"),
        @Json(name = "Wifi60") WIFI60("Wifi60")
    }
}
