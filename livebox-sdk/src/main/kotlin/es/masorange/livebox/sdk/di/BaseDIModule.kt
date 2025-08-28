package es.masorange.livebox.sdk.di

import org.kodein.di.DI

/**
 * Base [DI] Module
 */
abstract class BaseDIModule {
    abstract val builder: DI.Builder.() -> Unit

    /**
     * Create a new [DI.Module] with [builder]
     */
    fun create() = DI.Module(name = this::class.simpleName!!, allowSilentOverride = false, init = builder)
}
