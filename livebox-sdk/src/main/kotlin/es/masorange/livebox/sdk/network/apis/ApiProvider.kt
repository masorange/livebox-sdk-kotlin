package es.masorange.livebox.sdk.network.apis

import es.masorange.livebox.sdk.network.Environment

/**
 * Wrapper that provides the correct API instance based on the Livebox environment.
 */
class ApiProvider<T : Any>(val testInstance: T, val liveInstance: T) {
    /**
     * Returns an API instance based on environment.
     */
    fun get(environment: Environment): T {
        return when (environment) {
            Environment.TEST -> testInstance
            Environment.LIVE -> liveInstance
        }
    }
}
