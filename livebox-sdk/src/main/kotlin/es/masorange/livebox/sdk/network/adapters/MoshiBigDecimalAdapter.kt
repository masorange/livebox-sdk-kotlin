package es.masorange.livebox.sdk.network.adapters

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonReader
import com.squareup.moshi.JsonWriter
import com.squareup.moshi.Moshi
import java.math.BigDecimal

/**
 * A [Moshi] adapter for [BigDecimal].
 * To use, add this as an adapter for your enum type on your Moshi.Builder:
 *              Moshi moshi = new Moshi.Builder()
 *              .add(BigDecimal.class, MoshiBigDecimalAdapter())
 *              .build();
 */
class MoshiBigDecimalAdapter : JsonAdapter<BigDecimal>() {

    override fun fromJson(reader: JsonReader): BigDecimal {
        val string = reader.nextString()
        return BigDecimal(string)
    }

    override fun toJson(writer: JsonWriter, value: BigDecimal?) {
        val string = value.toString()
        writer.value(string)
    }
}
