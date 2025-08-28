package es.masorange.livebox.sdk.domain.livebox.models

import com.squareup.moshi.Json
import es.masorange.livebox.sdk.domain.livebox.models.BlockedDevice.Mode
import es.masorange.livebox.sdk.domain.livebox.models.BlockedDevice.Status

data class BlockedDevice(
    @Json(name = "MAC") val mac: String,
    @Json(name = "Mode") val mode: Mode? = null,
    @Json(name = "Name") val name: String,
    @Json(name = "Status") val status: Status,
    @Json(name = "Services") val services: List<BlockedDeviceService>? = null,
    @Json(name = "Urls") val urls: List<BlockedDeviceUrl>? = null,
    @Json(name = "Schedule") val schedule: List<DeviceSchedule>? = null
) {
    enum class Mode(val value: String) {
        @Json(name = "Allow") ALLOW("Allow"),
        @Json(name = "Block") BLOCK("Block"),
        @Json(name = "Schedule") SCHEDULE("Schedule")
    }

    enum class Status(val value: String) {
        @Json(name = "Enabled") ENABLED("Enabled"),
        @Json(name = "Disabled") DISABLED("Disabled")
    }
}

data class BlockedDeviceAddRule(
    @Json(name = "MAC") val mac: String,
    @Json(name = "Status") val status: Status,
)

data class BlockedDeviceUpdateRule(
    @Json(name = "Mode") val mode: Mode,
    @Json(name = "Status") val status: Status,
)