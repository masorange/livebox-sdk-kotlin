package es.masorange.livebox.sdk.domain.livebox.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

data class WlanInterface(
    @Json(name = "Id") val id: String,
    @Json(name = "Status") val status: Status,
    @Json(name = "Frequency") val frequency: Frequency,
    @Json(name = "LastChangeTime") val lastChangeTime: Int? = null,
    @Json(name = "LastChange") val lastChange: Int? = null,
    @Json(name = "AccessPoints") val accessPoints: List<ShortAccessPoint>,
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

    @JsonClass(generateAdapter = true)
    data class ShortAccessPoint(
        @Json(name = "Idx") val idx: String,
        @Json(name = "BSSID") val bssid: String,
        @Json(name = "Status") val status: Status,
        @Json(name = "RemainingDuration") val remainingDuration: Int? = null,
        @Json(name = "SSID") val ssid: String
    )

    @JsonClass(generateAdapter = true)
    data class ShortAccessPointJson(
        @Json(name = "Idx") val idx: String?,
        @Json(name = "BSSID") val bssid: String,
        @Json(name = "Status") val status: Status,
        @Json(name = "RemainingDuration") val remainingDuration: Int? = null,
        @Json(name = "SSID") val ssid: String
    )
}