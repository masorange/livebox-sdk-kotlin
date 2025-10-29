package es.masorange.livebox.sdk.domain.livebox.models

import com.squareup.moshi.Json

data class Enabled(
    @Json(name = "Enabled") val enabled: Boolean
)