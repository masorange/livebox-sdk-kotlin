package es.masorange.livebox.sdk

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import es.masorange.livebox.sdk.ui.login.LoginComponentScreen
import es.masorange.livebox.sdk.ui.theme.LiveboxSdkTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LiveboxSdkTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    LoginComponentScreen(
                        modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}
