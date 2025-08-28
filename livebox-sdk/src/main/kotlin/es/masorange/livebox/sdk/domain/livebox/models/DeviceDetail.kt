package es.masorange.livebox.sdk.domain.livebox.models

import com.squareup.moshi.Json

/**
 * Represents the detailed information of a device connected to the Livebox router.
 *
 * @param physAddress The physical (MAC) address of the device.
 * @param ipAddress The IPv4 address of the device.
 * @param ipV6Address The IPv6 address of the device, if available.
 * @param addressSource The source of the IP address (e.g., DHCP, Static), if available.
 * @param detectedTypes The types of connections detected for the device, if available.
 * @param leaseTimeRemaining The remaining lease time for the device's IP address, in seconds, if available.
 * @param vendorClassId The vendor class identifier of the device, if available.
 * @param clientId The client identifier of the device, if available.
 * @param userClassId The user class identifier of the device, if available.
 * @param hostName The hostname of the device, as seen by the router.
 * @param alias The alias or user-defined name for the device.
 * @param uPnPNames The UPnP names associated with the device, if available.
 * @param mDnsNames The mDNS names associated with the device, if available.
 * @param lLtdDevice Indicates whether the device supports LLTD (Link Layer Topology Discovery), if available.
 * @param ssid The SSID of the network the device is connected to.
 * @param active Indicates whether the device is currently active on the network.
 * @param lastConnection Indicates whether the device was the last to connect to the network.
 * @param tags Indicates whether the device has associated tags.
 * @param layer2Interface Indicates whether the device is connected via a Layer 2 interface.
 * @param interfaceType The type of interface used by the device (e.g., Ethernet, Wi-Fi).
 * @param manufacturerOui The manufacturer OUI (Organizationally Unique Identifier) of the device, if available.
 * @param serialNumber The serial number of the device, if available.
 * @param productClass The product class of the device, if available.
 * @param deviceIcon The icon representing the device, if available.
 * @param deviceLocation The location of the device, if available.
 * @param deviceType The type of the device (e.g., phone, computer).
 * @param deviceSource The source of the device information, if available.
 * @param deviceId The unique identifier of the device, if available.
 */
data class DeviceDetail(
    @Json(name = "physAddress") val physAddress: String,
    @Json(name = "ipAddress") val ipAddress: String,
    @Json(name = "ipV6Address") val ipV6Address: String,
    @Json(name = "addressSource") val addressSource: String? = null,
    @Json(name = "detectedTypes") val detectedTypes: String? = null,
    @Json(name = "leaseTimeRemaining") val leaseTimeRemaining: Int? = null,
    @Json(name = "vendorClassID") val vendorClassId: String? = null,
    @Json(name = "clientID") val clientId: String? = null,
    @Json(name = "userClassID") val userClassId: String? = null,
    @Json(name = "hostName") val hostName: String,
    @Json(name = "alias") val alias: String,
    @Json(name = "uPnPNames") val uPnPNames: String? = null,
    @Json(name = "mDNSNames") val mDnsNames: String? = null,
    @Json(name = "lLTDDevice") val lLtdDevice: Boolean? = null,
    @Json(name = "SSID") val ssid: String? = null,
    @Json(name = "active") val active: Boolean,
    @Json(name = "lastConnection") val lastConnection: String,
    @Json(name = "tags") val tags: String,
    @Json(name = "layer2Interface") val layer2Interface: String,
    @Json(name = "interfaceType") val interfaceType: InterfaceType,
    @Json(name = "manufacturerOUI") val manufacturerOui: String? = null,
    @Json(name = "serialNumber") val serialNumber: String? = null,
    @Json(name = "productClass") val productClass: String? = null,
    @Json(name = "deviceIcon") val deviceIcon: String? = null,
    @Json(name = "deviceLocation") val deviceLocation: String? = null,
    @Json(name = "deviceType") val deviceType: String,
    @Json(name = "deviceSource") val deviceSource: String? = null,
    @Json(name = "deviceID") val deviceId: String? = null
) {
    enum class InterfaceType(val value: String) {
        @Json(name = "Ethernet") ETHERNET("Ethernet"),
        @Json(name = "Wifi") WIFI("Wifi"),
        @Json(name = "Wifi24") WIFI24("Wifi24"),
        @Json(name = "Wifi50") WIFI50("Wifi50")
    }
}
