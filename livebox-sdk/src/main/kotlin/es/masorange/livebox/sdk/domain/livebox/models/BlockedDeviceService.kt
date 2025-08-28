package es.masorange.livebox.sdk.domain.livebox.models

import com.squareup.moshi.Json

data class BlockedDeviceService(
    @Json(name = "Id") val id: String? = null,
    @Json(name = "Name") val name: String? = null,
    @Json(name = "Ports") val ports: List<String>? = null,
    @Json(name = "Protocol") val protocol: Protocol? = null,
    @Json(name = "Status") val status: Status? = null,
) {
    enum class Protocol(val value: String) {
        @Json(name = "Tcp") TCP("Tcp"),
        @Json(name = "Udp") UDP("Udp"),
        @Json(name = "Both") BOTH("Both")
    }

    enum class Status(val value: String) {
        @Json(name = "Enabled") ENABLED("Enabled"),
        @Json(name = "Disabled") DISABLED("Disabled")
    }
}