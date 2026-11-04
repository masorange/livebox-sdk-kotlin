package es.masorange.livebox.sdk.domain.livebox.models

import com.squareup.moshi.Json

/**
 * Whether a feature is enabled, such as the Wi-Fi schedules of an access point.
 *
 * @param enabled `true` if the feature is enabled.
 */
data class Enabled(
    @Json(name = "Enabled") val enabled: Boolean
)