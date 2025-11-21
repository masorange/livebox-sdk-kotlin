package es.masorange.livebox.sdk.network.adapters

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonReader
import com.squareup.moshi.JsonWriter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import es.masorange.livebox.sdk.domain.livebox.models.DeviceInfo
import timber.log.Timber
import java.lang.reflect.Type

/**
 * A [JsonAdapter.Factory] for [List]<[DeviceInfo]> that handles malformed JSON objects
 * by skipping them instead of failing the entire parsing.
 *
 * This adapter is needed because some Livebox responses may contain malformed device objects
 * where field values are missing, causing field names and values to be incorrectly shifted.
 *
 * When a malformed object is encountered, it is logged and skipped, allowing the rest
 * of the valid devices to be parsed successfully.
 */
class DeviceInfoListAdapterFactory : JsonAdapter.Factory {
    override fun create(type: Type, annotations: Set<Annotation>, moshi: Moshi): JsonAdapter<*>? {
        val rawType = Types.getRawType(type)
        if (rawType != List::class.java) return null

        val elementType = Types.collectionElementType(type, List::class.java)
        if (Types.getRawType(elementType) != DeviceInfo::class.java) return null

        val deviceInfoAdapter = moshi.adapter<DeviceInfo>(elementType, annotations)
        return DeviceInfoListAdapter(deviceInfoAdapter)
    }
}

private class DeviceInfoListAdapter(
    private val deviceInfoAdapter: JsonAdapter<DeviceInfo>
) : JsonAdapter<List<DeviceInfo>>() {

    override fun fromJson(reader: JsonReader): List<DeviceInfo> {
        val devices = mutableListOf<DeviceInfo>()
        reader.beginArray()
        while (reader.hasNext()) {
            try {
                deviceInfoAdapter.fromJson(reader)?.let { devices.add(it) }
            } catch (e: Exception) {
                reader.skipValue()
                Timber.w("Skipping malformed device: ${e.message}")
            }
        }
        reader.endArray()
        return devices
    }

    override fun toJson(writer: JsonWriter, value: List<DeviceInfo>?) {
        writer.beginArray()
        value?.forEach { deviceInfoAdapter.toJson(writer, it) }
        writer.endArray()
    }
}
