package es.masorange.livebox.sdk.network.interceptors.models

import okhttp3.Interceptor
import okhttp3.OkHttpClient

/**
 * Type of context where we want to inject the interceptor.
 */
enum class TypedInterceptorContextType { APPLICATION, NETWORK }

/**
 * Wrapper over the [Interceptor] class that also wraps the interceptor context where we want to add the interceptor.
 *
 * If you want to read about the different context types where we can inject interceptors into, take a look at this:
 * https://square.github.io/okhttp/interceptors/
 *
 * Priority is used to sort interceptor to execute request in a certain order,
 * higher priority means the interceptor should be executed first
 */
data class TypedInterceptor(private val contextType: TypedInterceptorContextType, val interceptor: Interceptor, val priority: Int) {

    /** Add the interceptor to a [OkHttpClient.Builder] */
    fun attachTo(client: OkHttpClient.Builder) {
        when (contextType) {
            TypedInterceptorContextType.APPLICATION -> client.addInterceptor(interceptor)
            TypedInterceptorContextType.NETWORK -> client.addNetworkInterceptor(interceptor)
        }
    }
}

/** Returns a [TypedInterceptor] collection sorted by priority, higher priority -> first on collection*/
fun Iterable<TypedInterceptor>.sortByPriority() = this.sortedByDescending { it.priority }
