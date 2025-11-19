package es.masorange.livebox.sdk.network.adapters

import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson
import es.masorange.livebox.sdk.domain.livebox.models.AccessPoint

class AccessPointTypeAdapter {
    @FromJson
    fun fromJson(value: String): AccessPoint.Type {
        return AccessPoint.Type.fromValue(value)
    }

    @ToJson
    fun toJson(type: AccessPoint.Type): String {
        return type.value
    }
}
