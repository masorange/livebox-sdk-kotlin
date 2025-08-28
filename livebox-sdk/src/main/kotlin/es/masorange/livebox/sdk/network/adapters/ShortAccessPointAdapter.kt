package es.masorange.livebox.sdk.network.adapters

import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson
import es.masorange.livebox.sdk.domain.livebox.models.WlanInterface.*

class ShortAccessPointAdapter {
    @FromJson
    fun fromJson(json: ShortAccessPointJson): ShortAccessPoint {
        val resolvedIdx = json.idx ?: json.bssid.replace(":", "")
        return ShortAccessPoint(
            idx = resolvedIdx,
            bssid = json.bssid,
            status = json.status,
            remainingDuration = json.remainingDuration,
            ssid = json.ssid
        )
    }

    @ToJson
    fun toJson(obj: ShortAccessPoint): ShortAccessPointJson {
        return ShortAccessPointJson(
            idx = obj.idx,
            bssid = obj.bssid,
            status = obj.status,
            remainingDuration = obj.remainingDuration,
            ssid = obj.ssid
        )
    }
}