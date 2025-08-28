package es.masorange.livebox.sdk.domain.livebox.models

import com.squareup.moshi.Json

data class DeviceAlias(
    @Json(name = "alias") val alias: String
)