package es.masorange.livebox.sdk

import android.content.Context
import es.masorange.livebox.sdk.network.Environment

/**
 * Builder for instantiating [LiveboxSdk].
 *
 * @param context Context where the init is performed.
 * @param environment The environment to use (e.g., TEST or LIVE).
 */
class LiveboxSdkBuilder {
    private var context: Context? = null
    private var environment: Environment? = null

    fun withContext(context: Context) = apply { this.context = context }
    fun withEnvironment(environment: Environment) = apply { this.environment = environment }

    /**
     * Build the [LiveboxSdk] with the provided [Context] and [Environment].
     *
     * @return the [LiveboxSdk] instance.
     */
    fun build(): LiveboxSdk {
        requireNotNull(context)
        requireNotNull(environment)
        return LiveboxSdk(context!!, environment!!)
            .apply { init() }
    }
}