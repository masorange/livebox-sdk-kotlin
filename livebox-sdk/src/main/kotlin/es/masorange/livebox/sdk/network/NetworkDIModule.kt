package es.masorange.livebox.sdk.network

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import es.masorange.livebox.sdk.di.BaseDIModule
import es.masorange.livebox.sdk.network.adapters.AccessPointBandwidthConfAdapter
import es.masorange.livebox.sdk.network.adapters.FeatureIdAdapter
import es.masorange.livebox.sdk.network.adapters.MoshiBigDecimalAdapter
import es.masorange.livebox.sdk.network.adapters.ShortAccessPointAdapter
import es.masorange.livebox.sdk.network.adapters.WifiStatusButtonAdapter
import es.masorange.livebox.sdk.network.interceptors.INTERCEPTOR_LIVEBOX_SDK_AUTH_HEADER_APP
import es.masorange.livebox.sdk.network.interceptors.models.TypedInterceptor
import es.masorange.livebox.sdk.network.interceptors.INTERCEPTOR_LIVEBOX_SDK_HEADERS_APP_LIVE
import es.masorange.livebox.sdk.network.interceptors.INTERCEPTOR_LIVEBOX_SDK_HEADERS_APP_TEST
import okhttp3.Cache
import okhttp3.OkHttpClient
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.singleton
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.math.BigDecimal
import java.util.concurrent.TimeUnit

@Suppress("UndocumentedPublicClass")
object NetworkDIModule : BaseDIModule() {
    private const val OKHTTP_TIMEOUT_SECONDS = 60L
    private const val MAX_CACHE_SIZE_BYTES = 100L * 1024 * 1024 // 100MB

    internal const val RETROFIT_TEST_TAG = "test"
    internal const val RETROFIT_LIVE_TAG = "live"

    override val builder: DI.Builder.() -> Unit = {
        bind<OkHttpClient>(RETROFIT_TEST_TAG) with singleton {
            val client = getOkHttpBuilder(instance())
            val liveboxHeadersInterceptor = instance<TypedInterceptor>(INTERCEPTOR_LIVEBOX_SDK_HEADERS_APP_TEST).interceptor
            val liveboxAuthHeaderInterceptor = instance<TypedInterceptor>(INTERCEPTOR_LIVEBOX_SDK_AUTH_HEADER_APP).interceptor
            client
                .addInterceptor(ChuckerInterceptor.Builder(instance()).build())
                .addInterceptor(liveboxHeadersInterceptor)
                .addInterceptor(liveboxAuthHeaderInterceptor)
                .build()
        }

        bind<OkHttpClient>(RETROFIT_LIVE_TAG) with singleton {
            val client = getOkHttpBuilder(instance())
            val liveboxHeadersInterceptor = instance<TypedInterceptor>(INTERCEPTOR_LIVEBOX_SDK_HEADERS_APP_LIVE).interceptor
            val liveboxAuthHeaderInterceptor = instance<TypedInterceptor>(INTERCEPTOR_LIVEBOX_SDK_AUTH_HEADER_APP).interceptor
            client
                .addInterceptor(ChuckerInterceptor.Builder(instance()).build())
                .addInterceptor(liveboxHeadersInterceptor)
                .addInterceptor(liveboxAuthHeaderInterceptor)
                .build()
        }

        bind<Retrofit>(RETROFIT_TEST_TAG) with singleton {
            Retrofit.Builder()
                .client(instance(RETROFIT_TEST_TAG))
                .baseUrl(Environment.TEST.baseUrl)
                .addConverterFactory(instance())
                .build()
        }

        bind<Retrofit>(RETROFIT_LIVE_TAG) with singleton {
            Retrofit.Builder()
                .client(instance(RETROFIT_LIVE_TAG))
                .baseUrl(Environment.LIVE.baseUrl)
                .addConverterFactory(instance())
                .build()
        }

        bind<Moshi>() with singleton {
            Moshi.Builder()
                .add(ShortAccessPointAdapter())
                .add(KotlinJsonAdapterFactory())
                .add(FeatureIdAdapter())
                .add(AccessPointBandwidthConfAdapter())
                .add(WifiStatusButtonAdapter())
                .add(BigDecimal::class.java, MoshiBigDecimalAdapter())
                .build()
        }

        bind<Converter.Factory>() with singleton {
            MoshiConverterFactory.create(instance())
        }

        bind<Cache>() with singleton {
            val context: Context = instance()
            Cache(context.cacheDir, MAX_CACHE_SIZE_BYTES)
        }
    }

    private fun getOkHttpBuilder(cache: Cache, timeoutSeconds: Long = OKHTTP_TIMEOUT_SECONDS) =
        OkHttpClient.Builder()
            .cache(cache)
            .connectTimeout(timeoutSeconds, TimeUnit.SECONDS)
            .readTimeout(timeoutSeconds, TimeUnit.SECONDS)
            .writeTimeout(timeoutSeconds, TimeUnit.SECONDS)
}
