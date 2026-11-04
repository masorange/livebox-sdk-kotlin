package es.masorange.livebox.sdk.domain.livebox.models

import com.squareup.moshi.Json

/**
 * The request body to set the alias of a device.
 *
 * @param alias The alias to set.
 */
data class DeviceAlias(
    @Json(name = "alias") val alias: String
)