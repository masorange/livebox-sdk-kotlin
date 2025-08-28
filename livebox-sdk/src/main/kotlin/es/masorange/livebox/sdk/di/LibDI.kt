package es.masorange.livebox.sdk.di

import android.content.Context
import es.masorange.livebox.sdk.BuildConfig
import es.masorange.livebox.sdk.domain.livebox.LiveboxDIModule
import es.masorange.livebox.sdk.network.Environment
import es.masorange.livebox.sdk.network.NetworkDIModule
import es.masorange.livebox.sdk.network.interceptors.InterceptorsDIModule
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.conf.ConfigurableDI
import org.kodein.di.singleton
import timber.log.Timber
import java.lang.ref.WeakReference
import kotlin.collections.forEach

internal object LibDI {
    internal val di = ConfigurableDI(mutable = true)
    val context: Context?
        get() = contextRef?.get()

    private var contextRef: WeakReference<Context>? = null

    private lateinit var environment: Environment

    /**
     * Initializes dependency injection.
     */
    fun initializeInjection(context: Context,
                            environment: Environment) {
        this.contextRef = WeakReference(context)
        this.environment = environment

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        di.clear()

        with(di) {
            // First, add all dependencies
            addImports(
                // App
                AppDIModule.create(),
                // Interceptors
                InterceptorsDIModule.create(),
                // Network
                NetworkDIModule.create(),
                // Livebox
                LiveboxDIModule.create()
            )
        }
    }

    private fun ConfigurableDI.addImports(vararg moduleInfo: DI.Module) {
        moduleInfo.forEach { addImport(it, false) }
    }
}

/**
 * Kodein module that provides app dependencies
 */
internal object AppDIModule : BaseDIModule() {
    override val builder: DI.Builder.() -> Unit = {
        bind<Context>() with singleton { LibDI.context ?: throw IllegalStateException("Context is not initialized") }
    }
}
