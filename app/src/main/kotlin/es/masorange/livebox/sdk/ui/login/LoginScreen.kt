package es.masorange.livebox.sdk.ui.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import es.masorange.livebox.sdk.network.Environment
import es.masorange.livebox.sdk.ui.components.SwitchLabel
import es.masorange.livebox.sdk.ui.login.LoginViewModel.LoginState
import es.masorange.livebox.sdk.ui.theme.LiveboxSdkTheme

@Composable
fun LoginComponentScreen(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val loginViewModel: LoginViewModel = viewModel()

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val loginState = remember { mutableStateOf<LoginState>(LoginState.INIT) }
        val passwordState = rememberSaveable { mutableStateOf("") }
        val isTestEnvironmentState = rememberSaveable { mutableStateOf(false) }

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = passwordState.value,
            onValueChange = {
                passwordState.value = it
            },
            label = { Text("Password") }
        )

        Spacer(Modifier.height(16.dp))

        SwitchLabel(
            label = "Test environment",
            checkedState = isTestEnvironmentState
        )

        Spacer(Modifier.height(32.dp))

        Text(
            textAlign = TextAlign.Center,
            text = when (loginState.value) {
                is LoginState.INIT -> "Not logged"
                is LoginState.SUCCESS ->
                    "Logged to ${(loginState.value as LoginState.SUCCESS).message} successfully"
                is LoginState.LOADING ->
                    (loginState.value as LoginState.LOADING).message
                is LoginState.FAILURE ->
                    "Login failed\n(${(loginState.value as LoginState.FAILURE).message})"
            })

        Spacer(Modifier.height(16.dp))

        Button(
            onClick = {
                loginViewModel.connectToLivebox(
                    context = context,
                    environment = if (isTestEnvironmentState.value) {
                        Environment.TEST
                    } else {
                        Environment.LIVE
                    },
                    password = passwordState.value,
                    onLoginStateChanged = { state ->
                        loginState.value = state
                    })
                passwordState.value
            }) {
            Text("Login")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginComponentScreenPreview() {
    LiveboxSdkTheme {
        LoginComponentScreen()
    }
}