package starmage27.fontpreview

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen

class ViewTTFActivity : ComponentActivity() {
    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val fileUri = intent?.let { intent ->
            if (intent.action == Intent.ACTION_VIEW) {
                intent.data
            } else {
                null
            }
        }

        enableEdgeToEdge()
        installSplashScreen()
        setContent {
            fileUri?.let { uri ->
                mainViewModel.loadFile(uri)
            }
            RootScreen(mainViewModel = mainViewModel)
        }
    }
}

class ViewOTFActivity : ComponentActivity() {
    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val fileUri = intent?.let { intent ->
            if (intent.action == Intent.ACTION_VIEW) {
                intent.data
            } else {
                null
            }
        }

        enableEdgeToEdge()
        installSplashScreen()
        setContent {
            fileUri?.let { uri ->
                mainViewModel.loadFile(uri)
            }
            RootScreen(mainViewModel = mainViewModel)
        }
    }
}