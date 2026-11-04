package es.masorange.livebox.sdk.domain.livebox.models

import com.squareup.moshi.Json

/**
 * The configuration of a Wi-Fi access point (an SSID broadcast by a Wi-Fi interface).
 *
 * Get it with [LiveboxSdk.getAccessPoint][es.masorange.livebox.sdk.LiveboxSdk.getAccessPoint]. To change it, use [copy] on the
 * received value and send the result with [LiveboxSdk.updateAccessPoint][es.masorange.livebox.sdk.LiveboxSdk.updateAccessPoint].
 *
 * @param idx The access point index, if the router provides one. Use [WlanInterface.ShortAccessPoint.idx] as `wlanAp` in
 * requests, as it is always set.
 * @param bssid The BSSID (MAC address) of the access point.
 * @param type Whether this is the home or the guest network.
 * @param manner Whether the access point is combined with the other bands or split from them.
 * @param status Whether the access point is enabled.
 * @param ssid The network name (SSID).
 * @param password The network password.
 * @param ssidAdvertisementEnabled Whether the SSID is broadcast. When `false`, the network is hidden.
 * @param retryLimit The retry limit of the access point, if available.
 * @param wmmCapability Whether the access point supports Wi-Fi Multimedia (WMM), if available.
 * @param uapsdCapability Whether the access point supports Unscheduled Automatic Power Save Delivery (U-APSD), if available.
 * @param wmmEnable Whether Wi-Fi Multimedia (WMM) is enabled, if available.
 * @param uapsdEnable Whether Unscheduled Automatic Power Save Delivery (U-APSD) is enabled, if available.
 * @param maxStations The maximum number of devices that can connect at the same time, if available.
 * @param apBridgeDisable A bridge setting of the access point, if available.
 * @param channelConf The channel selection mode.
 * @param channel The channel currently in use.
 * @param bandwidthConf The configured channel bandwidth.
 * @param bandwidth The channel bandwidth currently in use (e.g., `"80MHz"`).
 * @param mode The Wi-Fi standards enabled on the access point (e.g., `"b/g/n"`), if available.
 * @param schedulingAllowed Whether Wi-Fi schedules can be used on this access point.
 */
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
    /** The network an access point belongs to. */
    enum class Type(val value: String) {
        /** The main (home) network. */
        @Json(name = "Home") HOME("Home"),
        /** The guest network. */
        @Json(name = "Guest") GUEST("Guest"),
        /** A network type not recognized by the SDK. */
        UNKNOWN("Unknown");

        companion object {
            /** Returns the [Type] matching [value], or [UNKNOWN] if it is not recognized. */
            fun fromValue(value: String?): Type =
                entries.find { it.value == value } ?: UNKNOWN
        }
    }

    /** How an access point is shared across frequency bands. */
    enum class Manner(val value: String) {
        /** The access point is shared with the other bands. */
        @Json(name = "Combined") COMBINED("Combined"),
        /** The access point is independent from the other bands. */
        @Json(name = "Split") SPLIT("Split")
    }

    /** The status of an access point. */
    enum class Status(val value: String) {
        /** The access point is enabled. */
        @Json(name = "Up") UP("Up"),
        /** The access point is disabled. */
        @Json(name = "Down") DOWN("Down")
    }

    /** The channel selection mode. The `Auto` variants let the router pick the channel. */
    enum class ChannelConf(val value: String) {
        @Json(name = "Auto") AUTO("Auto"),
        @Json(name = "Auto1") AUTO1("Auto1"),
        @Json(name = "Auto2") AUTO2("Auto2")
    }

    /** The configured channel bandwidth. */
    enum class BandwidthConf(val value: String) {
        /** The router picks the bandwidth. */
        @Json(name = "Auto") AUTO("Auto"),
        @Json(name = "20MHz") _20MHZ("20MHz"),
        @Json(name = "40MHz") _40MHZ("40MHz"),
        @Json(name = "80MHz") _80MHZ("80MHz"),
        @Json(name = "160MHz") _160MHZ("160MHz"),
        @Json(name = "20/40MHz") _20_40MHZ("20/40MHz"),
        @Json(name = "80/40/20MHz") _80_40_20MHZ("80/40/20MHz"),
        /** A bandwidth not recognized by the SDK. */
        UNKNOWN("Unknown");

        companion object {
            /** Returns the [BandwidthConf] matching [value], or [UNKNOWN] if it is not recognized. */
            fun fromValue(value: String?): BandwidthConf =
                entries.find { it.value == value } ?: UNKNOWN
        }
    }
}