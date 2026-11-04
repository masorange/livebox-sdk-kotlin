package es.masorange.livebox.sdk.domain.livebox.models

import com.squareup.moshi.Json
import es.masorange.livebox.sdk.domain.livebox.models.BlockedDevice.Mode
import es.masorange.livebox.sdk.domain.livebox.models.BlockedDevice.Status

/**
 * A device with parental control rules, as returned by [LiveboxSdk.getBlockedDevices][es.masorange.livebox.sdk.LiveboxSdk.getBlockedDevices]
 * and [LiveboxSdk.getBlockedDevice][es.masorange.livebox.sdk.LiveboxSdk.getBlockedDevice].
 *
 * @param mac The MAC address of the device.
 * @param mode How the device access is restricted, if available.
 * @param name The name of the device.
 * @param status Whether the rules are enabled for the device.
 * @param services The blocked services of the device, if available.
 * @param urls The blocked URLs of the device, if available.
 * @param schedule The time slots during which the device has no access to the network, if available.
 */
data class BlockedDevice(
    @Json(name = "MAC") val mac: String,
    @Json(name = "Mode") val mode: Mode? = null,
    @Json(name = "Name") val name: String,
    @Json(name = "Status") val status: Status,
    @Json(name = "Services") val services: List<BlockedDeviceService>? = null,
    @Json(name = "Urls") val urls: List<BlockedDeviceUrl>? = null,
    @Json(name = "Schedule") val schedule: List<Schedule>? = null
) {
    /** How the device access is restricted. */
    enum class Mode(val value: String) {
        /** The device always has access. */
        @Json(name = "Allow") ALLOW("Allow"),
        /** The device never has access. */
        @Json(name = "Block") BLOCK("Block"),
        /** The device has no access during its [schedule]. */
        @Json(name = "Schedule") SCHEDULE("Schedule")
    }

    /** Whether the parental control rules are enabled. */
    enum class Status(val value: String) {
        @Json(name = "Enabled") ENABLED("Enabled"),
        @Json(name = "Disabled") DISABLED("Disabled")
    }
}

/**
 * The request body to add a device to the parental control list.
 *
 * @param mac The MAC address of the device.
 * @param status Whether the rules are enabled for the device.
 */
data class BlockedDeviceAddRule(
    @Json(name = "MAC") val mac: String,
    @Json(name = "Status") val status: Status,
)

/**
 * The request body to update the parental control rules of a device.
 *
 * @param mode How the device access is restricted.
 * @param status Whether the rules are enabled for the device.
 */
data class BlockedDeviceUpdateRule(
    @Json(name = "Mode") val mode: Mode,
    @Json(name = "Status") val status: Status,
)