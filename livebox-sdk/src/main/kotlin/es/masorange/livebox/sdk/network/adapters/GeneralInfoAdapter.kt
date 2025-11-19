package es.masorange.livebox.sdk.network.adapters

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonReader
import com.squareup.moshi.JsonWriter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import es.masorange.livebox.sdk.domain.livebox.models.GeneralInfo
import java.lang.reflect.Type

/**
 * A [JsonAdapter] for [GeneralInfo] that handles both "Manufacturer" and "ManuFacturer"
 * field names from the JSON response.
 *
 * This adapter is needed because some Livebox responses use "ManuFacturer" (with capital F)
 * instead of the standard "Manufacturer" field name.
 */
class GeneralInfoAdapterFactory : JsonAdapter.Factory {
    override fun create(type: Type, annotations: Set<Annotation>, moshi: Moshi): JsonAdapter<*>? {
        return if (Types.getRawType(type) == GeneralInfo::class.java) {
            GeneralInfoAdapter(moshi.nextAdapter(this, type, annotations))
        } else null
    }
}

private class GeneralInfoAdapter(
    private val delegate: JsonAdapter<GeneralInfo>
) : JsonAdapter<GeneralInfo>() {
    override fun fromJson(reader: JsonReader): GeneralInfo? {
        val jsonObject = reader.readJsonValue() as? Map<*, *> ?: return null
        val normalizedMap = jsonObject.mapKeys {
            if (it.key == "ManuFacturer") "Manufacturer" else it.key
        }
        return delegate.fromJsonValue(normalizedMap)
    }

    override fun toJson(writer: JsonWriter, value: GeneralInfo?) {
        delegate.toJson(writer, value)
    }
}
