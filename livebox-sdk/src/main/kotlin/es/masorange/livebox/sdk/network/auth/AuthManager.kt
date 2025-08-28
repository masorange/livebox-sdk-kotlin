package es.masorange.livebox.sdk.network.auth

import android.util.Base64

object AuthManager {
    private const val USER_NAME = "UsrAdmin"

    private var encodedCredentials: String? = null

    val isLoggedIn: Boolean
        get() = encodedCredentials != null

    fun setCredentials(username: String? = USER_NAME,
                       password: String) {
        val userPass = "$username:$password"
        encodedCredentials = Base64.encodeToString(userPass.toByteArray(), Base64.NO_WRAP)
    }

    fun clearCredentials() {
        encodedCredentials = null
    }

    fun getAuthorizationHeader(): String? {
        return encodedCredentials?.let { "Basic $it" }
    }
}