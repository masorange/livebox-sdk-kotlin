package es.masorange.livebox.sdk.domain.livebox.models

import com.squareup.moshi.Json

/**
 * A URL blocked for a device by parental control.
 *
 * @param id The identifier of the rule, if available.
 * @param key The blocked URL, if available.
 * @param status Whether the block is enabled, if available.
 */
data class BlockedDeviceUrl(
    @Json(name = "Id") val id: String? = null,
    @Json(name = "Key") val key: String? = null,
    @Json(name = "Status") val status: Status? = null,
) {
    /** Whether the block is enabled. */
    enum class Status(val value: String) {
        @Json(name = "Enabled") ENABLED("Enabled"),
        @Json(name = "Disabled") DISABLED("Disabled")
    }
}