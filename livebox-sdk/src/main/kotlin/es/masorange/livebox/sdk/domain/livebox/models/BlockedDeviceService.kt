package es.masorange.livebox.sdk.domain.livebox.models

import com.squareup.moshi.Json

/**
 * A network service blocked for a device by parental control.
 *
 * @param id The identifier of the service, if available.
 * @param name The name of the service, if available.
 * @param ports The ports of the service, if available.
 * @param protocol The protocol of the service, if available.
 * @param status Whether the block is enabled, if available.
 */
data class BlockedDeviceService(
    @Json(name = "Id") val id: String? = null,
    @Json(name = "Name") val name: String? = null,
    @Json(name = "Ports") val ports: List<String>? = null,
    @Json(name = "Protocol") val protocol: Protocol? = null,
    @Json(name = "Status") val status: Status? = null,
) {
    /** The transport protocol of a service. */
    enum class Protocol(val value: String) {
        @Json(name = "Tcp") TCP("Tcp"),
        @Json(name = "Udp") UDP("Udp"),
        /** Both TCP and UDP. */
        @Json(name = "Both") BOTH("Both")
    }

    /** Whether the block is enabled. */
    enum class Status(val value: String) {
        @Json(name = "Enabled") ENABLED("Enabled"),
        @Json(name = "Disabled") DISABLED("Disabled")
    }
}