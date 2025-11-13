package es.masorange.livebox.sdk.domain.livebox.models

import com.squareup.moshi.Json

/**
 * Represents the information of a device connected to the Livebox router.
 *
 * @param ipAddress The IP address of the device.
 * @param physAddress The physical (MAC) address of the device.
 * @param hostName The name of the device, which the user will see.
 * @param interfaceType The type of interface used by the device (e.g., Ethernet, Wi-Fi).
 * @param active Indicates whether the device is currently active.
 * @param alias An alias or name for the device, if available.
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
    enum class InterfaceType(val value: String) {
        @Json(name = "Ethernet") ETHERNET("Ethernet"),
        @Json(name = "Wifi") WIFI("Wifi"),
        @Json(name = "Wifi24") WIFI24("Wifi24"),
        @Json(name = "Wifi50") WIFI50("Wifi50")
    }
}
