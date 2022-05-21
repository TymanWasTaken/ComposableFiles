package tech.tyman.composablefiles

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.Settings
import android.widget.Toast
//import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResultLauncher
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import tech.tyman.composablefiles.ui.screens.MainScreen
import tech.tyman.composablefiles.ui.theme.ComposableFilesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val uri = Uri.parse("package:${BuildConfig.APPLICATION_ID}")

        if (!Environment.isExternalStorageManager()) startActivityForResult(
            Intent(
                Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION,
                uri
            ),
            9894
        )
        else onPermissionsGranted()
    }

    /**
     * Yes this is deprecated but the new method doesn't even work lmao
     */
    @Deprecated("I hate android")
    override fun onActivityResult(request: Int, result: Int, intent: Intent?) {
        super.onActivityResult(request, result, intent)

        when (request) {
            9894 -> {
                if (Environment.isExternalStorageManager()) onPermissionsGranted()
                else Toast.makeText(this, "Fuck", Toast.LENGTH_SHORT).show()
            }
            else -> return
        }
    }

    private fun onPermissionsGranted() {
        setContent {
            ComposableFilesTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainScreen()
                }
            }
        }
    }
}