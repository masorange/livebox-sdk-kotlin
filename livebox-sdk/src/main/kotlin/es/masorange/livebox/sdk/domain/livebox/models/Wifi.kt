package es.masorange.livebox.sdk.domain.livebox.models

import com.squareup.moshi.Json
import es.masorange.livebox.sdk.network.adapters.FlexibleBoolean

data class Wifi(
    @Json(name = "Id") val id: String? = null,
    @FlexibleBoolean @Json(name = "WiFiStatusButton") val wifiStatusButton: Boolean? = null,
    @Json(name = "Status") val status: Status? = null,
    @Json(name = "Frequency") val frequency: Frequency? = null
) {
    enum class Status(val value: String) {
        @Json(name = "Up") UP("Up"),
        @Json(name = "Down") DOWN("Down")
    }

    enum class Frequency(val value: String) {
        @Json(name = "2.4GHz") _24GHZ("2.4GHz"),
        @Json(name = "5GHz") _5GHZ("5GHz"),
        @Json(name = "6GHz") _6GHZ("6GHz")
    }
}