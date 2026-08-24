package es.masorange.livebox.sdk

import android.Manifest
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import es.masorange.livebox.sdk.ui.login.LoginComponentScreen
import es.masorange.livebox.sdk.ui.theme.LiveboxSdkTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    private var permissionDenied by mutableStateOf(false)

    private val localNetworkPermission = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (!isGranted) {
            permissionDenied = true
        }
        setupUI()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.CINNAMON_BUN) {
            localNetworkPermission.launch(Manifest.permission.ACCESS_LOCAL_NETWORK)
        } else {
            setupUI()
        }
    }

    private fun setupUI() {
        setContent {
            LiveboxSdkTheme {
                val snackbarHostState = remember { SnackbarHostState() }
                val scope = rememberCoroutineScope()

                LaunchedEffect(permissionDenied) {
                    if (permissionDenied) {
                        scope.launch {
                            snackbarHostState.showSnackbar(
                                message = "Local Network permission is needed for connecting to the Router",
                                actionLabel = "OK"
                            )
                        }
                    }
                }

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    snackbarHost = { SnackbarHost(snackbarHostState) }
                ) { innerPadding ->
                    LoginComponentScreen(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}
