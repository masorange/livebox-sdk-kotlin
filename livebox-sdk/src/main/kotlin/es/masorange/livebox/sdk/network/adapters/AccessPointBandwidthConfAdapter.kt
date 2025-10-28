package es.masorange.livebox.sdk.network.adapters

import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson
import es.masorange.livebox.sdk.domain.livebox.models.AccessPoint

class AccessPointBandwidthConfAdapter {
    @FromJson
    fun fromJson(value: String): AccessPoint.BandwidthConf {
        return AccessPoint.BandwidthConf.fromValue(value)
    }

    @ToJson
    fun toJson(id: AccessPoint.BandwidthConf): String {
        return id.value
    }
}