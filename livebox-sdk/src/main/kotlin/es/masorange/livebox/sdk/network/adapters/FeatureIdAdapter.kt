package es.masorange.livebox.sdk.network.adapters

import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson
import es.masorange.livebox.sdk.domain.livebox.models.Feature

class FeatureIdAdapter {
    @FromJson
    fun fromJson(value: String): Feature.Id {
        return Feature.Id.fromValue(value)
    }

    @ToJson
    fun toJson(id: Feature.Id): String {
        return id.value
    }
}