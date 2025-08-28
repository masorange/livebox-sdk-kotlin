package es.masorange.livebox.sdk.network.interceptors

import es.masorange.livebox.sdk.network.auth.AuthManager
import okhttp3.Interceptor
import okhttp3.Response

const val AUTHORIZATION_HEADER = "Authorization"

/**
 *  [Interceptor] that attaches the "user:pass" header Base64 encoded for requests.
 */
class LiveboxSdkAuthHeaderInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = chain.request().newBuilder()

        AuthManager.getAuthorizationHeader()?.let { auth ->
            requestBuilder.addHeader(AUTHORIZATION_HEADER, auth)
        }

        return chain.proceed(requestBuilder.build())
    }
}