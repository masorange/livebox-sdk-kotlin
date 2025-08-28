package es.masorange.livebox.sdk.domain.livebox

import es.masorange.livebox.sdk.di.BaseDIModule
import es.masorange.livebox.sdk.network.NetworkDIModule
import es.masorange.livebox.sdk.network.apis.ApiProvider
import es.masorange.livebox.sdk.network.apis.livebox.LiveboxApi
import es.masorange.livebox.sdk.network.apis.livebox.LiveboxApiFake
import es.masorange.livebox.sdk.utils.getClassInstance
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.singleton
import retrofit2.Retrofit

@Suppress("UndocumentedPublicClass")
object LiveboxDIModule : BaseDIModule() {
    override val builder: DI.Builder.() -> Unit = {
        bind<ApiProvider<LiveboxApi>>() with singleton {
            ApiProvider(
                testInstance = getClassInstance(
                    productionInstance = { instance<Retrofit>(NetworkDIModule.RETROFIT_TEST_TAG).create(LiveboxApi::class.java) },
                    fakeInstance = { LiveboxApiFake() }
                ),
                liveInstance = getClassInstance(
                    productionInstance = { instance<Retrofit>(NetworkDIModule.RETROFIT_LIVE_TAG).create(LiveboxApi::class.java) },
                    fakeInstance = { LiveboxApiFake() }
                )
            )
        }
    }
}
