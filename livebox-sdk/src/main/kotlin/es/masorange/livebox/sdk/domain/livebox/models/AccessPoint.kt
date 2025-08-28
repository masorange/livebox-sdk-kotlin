package es.masorange.livebox.sdk.domain.livebox.models

import com.squareup.moshi.Json

data class AccessPoint(
    @Json(name = "idx") val idx: String? = null,
    @Json(name = "BSSID") val bssid: String,
    @Json(name = "Type") val type: Type,
    @Json(name = "Manner") val manner: Manner,
    @Json(name = "Status") val status: Status,
    @Json(name = "SSID") val ssid: String,
    @Json(name = "Password") val password: String,
    @Json(name = "SSIDAdvertisementEnabled") val ssidAdvertisementEnabled: Boolean? = null,
    @Json(name = "RetryLimit") val retryLimit: Int? = null,
    @Json(name = "WMMCapability") val wmmCapability: Boolean? = null,
    @Json(name = "UAPSDCapability") val uapsdCapability: Boolean? = null,
    @Json(name = "WMMEnable") val wmmEnable: Boolean? = null,
    @Json(name = "UAPSDEnable") val uapsdEnable: Boolean? = null,
    @Json(name = "MaxStations") val maxStations: Int? = null,
    @Json(name = "APBridgeDisable") val apBridgeDisable: Boolean? = null,
    @Json(name = "ChannelConf") val channelConf: ChannelConf,
    @Json(name = "Channel") val channel: Int,
    @Json(name = "BandwithConf") val bandwidthConf: BandwidthConf,
    @Json(name = "Bandwith") val bandwidth: String,
    @Json(name = "Mode") val mode: String? = null,
    @Json(name = "SchedulingAllowed") val schedulingAllowed: Boolean
) {
    enum class Type(val value: String) {
        @Json(name = "Home") HOME("Home"),
        @Json(name = "Guest") GUEST("Guest")
    }

    enum class Manner(val value: String) {
        @Json(name = "Combined") COMBINED("Combined"),
        @Json(name = "Split") SPLIT("Split")
    }

    enum class Status(val value: String) {
        @Json(name = "Up") UP("Up"),
        @Json(name = "Down") DOWN("Down")
    }

    enum class ChannelConf(val value: String) {
        @Json(name = "Auto") AUTO("Auto"),
        @Json(name = "Auto1") AUTO1("Auto1"),
        @Json(name = "Auto2") AUTO2("Auto2")
    }

    enum class BandwidthConf(val value: String) {
        @Json(name = "Auto") AUTO("Auto"),
        @Json(name = "20MHz") _20MHZ("20MHz"),
        @Json(name = "20/40MHz") _20_40MHZ("20/40MHz"),
        @Json(name = "80MHz") _80MHZ("80MHz"),
        @Json(name = "160MHz") _160MHZ("160MHz")
    }
}