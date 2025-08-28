package es.masorange.livebox.sdk.domain.livebox.models

import com.squareup.moshi.Json

data class BlockedDeviceUrl(
    @Json(name = "Id") val id: String? = null,
    @Json(name = "Key") val key: String? = null,
    @Json(name = "Status") val status: Status? = null,
) {
    enum class Status(val value: String) {
        @Json(name = "Enabled") ENABLED("Enabled"),
        @Json(name = "Disabled") DISABLED("Disabled")
    }
}