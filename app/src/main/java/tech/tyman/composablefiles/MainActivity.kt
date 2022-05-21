package tech.tyman.composablefiles

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.Settings
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import tech.tyman.composablefiles.ui.screens.MainScreen
import tech.tyman.composablefiles.ui.theme.ComposableFilesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requestPermissions("MANAGE_EXTERNAL_STORAGE") { granted ->
            if (granted) {
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
            } else {
                Toast.makeText(this, "ComposableFiles requires access to your files to work!", Toast.LENGTH_SHORT).show()
                finishAndRemoveTask()
            }
        }
    }
}