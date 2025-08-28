package es.masorange.livebox.sdk.network.interceptors

import okhttp3.Interceptor
import okhttp3.Response

internal const val ACCEPT_HEADER = "Accept"
internal const val HOST_HEADER = "Host"
internal const val ACCEPT_ENCODING_HEADER = "Accept-Encoding"
internal const val USER_AGENT_HEADER = "User-Agent"
internal const val CONNECTION_HEADER = "Connection"

/**
 *  [Interceptor] that attaches Livebox headers for requests.
 */
internal class LiveboxSdkHeadersInterceptor(val host: String) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val builder = chain.request().newBuilder()

        val newRequest = builder
            .header(ACCEPT_HEADER, "application/json")
            .header(HOST_HEADER, host)
            .header(ACCEPT_ENCODING_HEADER, "gzip")
            .header(USER_AGENT_HEADER, "okhttp/4.12.0")
            .header(CONNECTION_HEADER, "keep-alive")
            .build()

        return chain.proceed(newRequest)
    }
}