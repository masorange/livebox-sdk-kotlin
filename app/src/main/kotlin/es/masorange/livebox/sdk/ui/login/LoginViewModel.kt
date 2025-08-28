package es.masorange.livebox.sdk.ui.login

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import es.masorange.livebox.sdk.LiveboxSdk
import es.masorange.livebox.sdk.LiveboxSdkBuilder
import es.masorange.livebox.sdk.network.Environment
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {
    sealed class LoginState(open val message: String = "") {
        object INIT : LoginState()
        data class LOADING(override val message: String) : LoginState(message)
        data class SUCCESS(override val message: String) : LoginState(message)
        data class FAILURE(override val message: String) : LoginState(message)
    }

    private lateinit var liveboxSdk: LiveboxSdk

    fun connectToLivebox(context: Context,
                         environment: Environment,
                         password: String,
                         onLoginStateChanged: (LoginState) -> Unit = {}) {
        onLoginStateChanged(LoginState.LOADING("Logging in..."))
        try {
            liveboxSdk = LiveboxSdkBuilder()
                .withContext(context = context)
                .withEnvironment(environment = environment)
                .build()

            login(
                password = password,
                onLoginStateChanged = onLoginStateChanged)
        } catch (e: Exception) {
            e.printStackTrace()
            onLoginStateChanged(LoginState.FAILURE("${e.localizedMessage}"))
        }
    }

    private fun login(password: String,
                      onLoginStateChanged: (LoginState) -> Unit = {}) {
        viewModelScope.launch {
            try {
                val login = liveboxSdk.login(password = password)
                val routerName = if (login) {
                    liveboxSdk.getGeneralInfo().routerName
                } else null
                onLoginStateChanged(
                    if (login) LoginState.SUCCESS(routerName ?: "[Unknown router]")
                    else LoginState.FAILURE("Invalid password"))
            } catch (e: Exception) {
                e.printStackTrace()
                onLoginStateChanged(LoginState.FAILURE("${e.localizedMessage}"))
            }
        }
    }
}