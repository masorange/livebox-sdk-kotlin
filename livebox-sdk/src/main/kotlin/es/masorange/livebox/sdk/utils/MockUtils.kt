package es.masorange.livebox.sdk.utils

import es.masorange.livebox.sdk.BuildConfig
import kotlinx.coroutines.delay
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.ResponseBody.Companion.toResponseBody
import retrofit2.HttpException
import retrofit2.Response
import timber.log.Timber
import kotlin.random.Random

/**
 * Provides prod or fake instance depending on debug and mock flags.
 * Check [docs/mocks.md] how to setup yor mock environment.
 */
internal fun <T> getClassInstance(
    mocksEnabled: Boolean = BuildConfig.MOCKS_ENABLED,
    productionInstance: () -> T,
    fakeInstance: () -> T
): T {
    val instance: T
    if (mocksEnabled) {
        instance = fakeInstance()
        Timber.v("Fake instance selected: $instance")
    } else {
        instance = productionInstance()
        Timber.v("Production instance selected: $instance")
    }
    return instance
}

private val serverDegradationException = HttpException(
    Response.error<Unit>(
        500,
        "".toResponseBody("application/json".toMediaType())
    )
)

/**
 * Delays the execution of the given function according to [BuildConfig] values.
 */
internal suspend fun <T> withDelay(delayMillis: Long = BuildConfig.MOCK_SERVER_MAX_DELAY_MILLIS,
                                   fn: () -> T): T {
    val delayMs = if (delayMillis == 0L) 0L else Random.nextLong(0, delayMillis)
    delay(delayMs)
    return fn()
}

/**
 * Fakes degrading server performance. Returns the result of the given function.
 */
internal suspend fun <T> withDegradedServer(serverErrorPercentage: Double = BuildConfig.MOCK_SERVER_ERROR_PERCENTAGE,
                                            throwable: Throwable = serverDegradationException,
                                            fn: () -> T): T {
    return withDelay {
        val shouldSucceed = Random.nextDouble() > serverErrorPercentage
        if (shouldSucceed) {
            fn()
        } else {
            throw throwable
        }
    }
}

/**
 * Fakes degrading server performance. Returns a [Response] object with the result of the given function.
 */
internal suspend fun <T> withDegradedServerResponse(serverErrorPercentage: Double = BuildConfig.MOCK_SERVER_ERROR_PERCENTAGE,
                                                    throwable: Throwable = serverDegradationException,
                                                    fn: () -> T): Response<T> {
    return withDelay {
        val shouldSucceed = Random.nextDouble() > serverErrorPercentage
        if (shouldSucceed) {
            Response.success(fn())
        } else {
            throw throwable
        }
    }
}
