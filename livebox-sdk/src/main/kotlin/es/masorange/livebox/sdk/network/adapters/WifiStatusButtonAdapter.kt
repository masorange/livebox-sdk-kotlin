package es.masorange.livebox.sdk.network.adapters

import com.squareup.moshi.FromJson
import com.squareup.moshi.JsonQualifier
import com.squareup.moshi.JsonReader
import com.squareup.moshi.JsonWriter
import com.squareup.moshi.ToJson

/**
 * Qualifier annotation for WiFi status button fields that can be received as either Boolean or String.
 */
@Retention(AnnotationRetention.RUNTIME)
@JsonQualifier
annotation class FlexibleBoolean

/**
 * Adapter for WiFi status button field that can be received as either a Boolean or a String.
 * Some devices return this field as a string ("true"/"false") while others return it as a proper boolean.
 *
 * Usage: Add @FlexibleBoolean annotation to fields that need this adapter.
 */
class WifiStatusButtonAdapter {

    @FromJson
    @FlexibleBoolean
    fun fromJson(reader: JsonReader): Boolean? = when (reader.peek()) {
        JsonReader.Token.BOOLEAN -> reader.nextBoolean()
        JsonReader.Token.STRING -> parseStringToBoolean(reader.nextString())
        JsonReader.Token.NULL -> reader.nextNull<Unit>().let { null }
        else -> reader.skipValue().let { null }
    }

    @ToJson
    fun toJson(writer: JsonWriter, @FlexibleBoolean value: Boolean?) {
        writer.run { if (value != null) value(value) else nullValue() }
    }

    private fun parseStringToBoolean(value: String): Boolean? = when (value.lowercase()) {
        "true" -> true
        "false" -> false
        else -> null
    }
}
