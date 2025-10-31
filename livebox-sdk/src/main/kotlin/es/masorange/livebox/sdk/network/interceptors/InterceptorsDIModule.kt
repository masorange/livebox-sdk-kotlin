package es.masorange.livebox.sdk.network.interceptors

import es.masorange.livebox.sdk.di.BaseDIModule
import es.masorange.livebox.sdk.network.Environment
import es.masorange.livebox.sdk.network.interceptors.models.TypedInterceptor
import es.masorange.livebox.sdk.network.interceptors.models.TypedInterceptorContextType
import org.kodein.di.*

private const val LIVEBOX_SDK_HEADERS_INTERCEPTOR_PRIORITY = 1
private const val LIVEBOX_SDK_HOST_HEADER_PREFIX = "http://"

const val INTERCEPTOR_LIVEBOX_SDK_HEADERS_APP_TEST = "livebox_sdk_headers_app_test"
const val INTERCEPTOR_LIVEBOX_SDK_HEADERS_APP_LIVE = "livebox_sdk_headers_app_live"
const val INTERCEPTOR_LIVEBOX_SDK_AUTH_HEADER_APP = "livebox_sdk_auth_header_app"

@Suppress("UndocumentedPublicClass")
object InterceptorsDIModule : BaseDIModule() {
    override val builder: DI.Builder.() -> Unit = {
        bind<TypedInterceptor>(INTERCEPTOR_LIVEBOX_SDK_HEADERS_APP_TEST) with singleton {
            val liveboxSdkHeadersInterceptor = LiveboxSdkHeadersInterceptor(
                host = Environment.TEST.baseUrl.removePrefix(LIVEBOX_SDK_HOST_HEADER_PREFIX)
            )
            TypedInterceptor(
                TypedInterceptorContextType.APPLICATION,
                liveboxSdkHeadersInterceptor,
                LIVEBOX_SDK_HEADERS_INTERCEPTOR_PRIORITY)
        }
        bind<TypedInterceptor>(INTERCEPTOR_LIVEBOX_SDK_HEADERS_APP_LIVE) with singleton {
            val liveboxSdkHeadersInterceptor = LiveboxSdkHeadersInterceptor(
                host = Environment.LIVE.baseUrl.removePrefix(LIVEBOX_SDK_HOST_HEADER_PREFIX)
            )
            TypedInterceptor(
                TypedInterceptorContextType.APPLICATION,
                liveboxSdkHeadersInterceptor,
                LIVEBOX_SDK_HEADERS_INTERCEPTOR_PRIORITY)
        }
        bind<TypedInterceptor>(INTERCEPTOR_LIVEBOX_SDK_AUTH_HEADER_APP) with singleton {
            val liveboxSdkAuthHeaderInterceptor = LiveboxSdkAuthHeaderInterceptor()
            TypedInterceptor(
                TypedInterceptorContextType.APPLICATION,
                liveboxSdkAuthHeaderInterceptor,
                LIVEBOX_SDK_HEADERS_INTERCEPTOR_PRIORITY)
        }
    }
}
