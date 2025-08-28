package es.masorange.livebox.sdk.domain.livebox.models

import com.squareup.moshi.Json

/**
 * The Features the router implements.
 * Note that the URIs may vary depending on the router model and firmware version.
 *
 * @param features The list of [Feature] objects which the router can do.
 */

data class Capabilities(
    @Json(name = "Features") val features: List<Feature>
) {
    val featuresMap: Map<Feature.Id, Feature> by lazy {
        features.associateBy { it.id }
    }
}
