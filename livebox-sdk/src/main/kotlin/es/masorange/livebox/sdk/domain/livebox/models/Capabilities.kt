package es.masorange.livebox.sdk.domain.livebox.models

import com.squareup.moshi.Json

/**
 * The features the router implements, as returned by the `API/Capabilities` endpoint.
 *
 * The SDK uses them to build the URL of each request, as the URIs may vary depending on the router model and
 * firmware version.
 *
 * @param features The list of [Feature] objects the router supports.
 */
data class Capabilities(
    @Json(name = "Features") val features: List<Feature>
) {
    /** The [features] indexed by their [Feature.Id]. */
    val featuresMap: Map<Feature.Id, Feature> by lazy {
        features.associateBy { it.id }
    }
}
