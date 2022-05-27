package tech.tyman.composablefiles

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Build.VERSION.SDK_INT
import android.os.Bundle
import android.os.Environment
import android.provider.Settings
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.core.app.ActivityCompat
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import tech.tyman.composablefiles.data.filesystems.LocalFileSystem
import tech.tyman.composablefiles.ui.screens.DirectoryScreen
import tech.tyman.composablefiles.ui.theme.ComposableFilesTheme
import tech.tyman.composablefiles.utils.urlEncode
import java.lang.IllegalArgumentException

class MainActivity : ComponentActivity() {
    /**
     * The main activity to initialize everything, this initiates the permissions flow if needed then renders the compose app
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (SDK_INT >= Build.VERSION_CODES.R) {
            // Android 10 introduced the MANAGE_EXTERNAL_STORAGE permission, so go through the flow of requesting that
            val uri = Uri.parse("package:${BuildConfig.APPLICATION_ID}")

            if (!Environment.isExternalStorageManager()) startActivityForResult(
                Intent(
                    Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION,
                    uri
                ),
                9894
            )
            else onPermissionsGranted()
        } else if (SDK_INT >= Build.VERSION_CODES.M)
            // Below 10 had both READ_EXTERNAL_STORAGE and WRITE_EXTERNAL_STORAGE, so request those normally
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE),
                9894
            )
        else
            // Below 6 asks for permissions while installing, so you don't have to request them
            this.onPermissionsGranted()
    }

    /**
     * This is deprecated but the new method doesn't even work for some reason, so I have to use it.
     * This is the callback for versions less than 10, with the new MANAGE_EXTERNAL_STORAGE permission.
     */
    @RequiresApi(Build.VERSION_CODES.R)
    override fun onActivityResult(request: Int, result: Int, intent: Intent?) {
        super.onActivityResult(request, result, intent)
        if (request != 9894) return

        if (Environment.isExternalStorageManager()) this.onPermissionsGranted()
        else this.onPermissionsRejected()
    }

    /**
     * This is also deprecated, but I have to use it to support android versions below 10.
     * This is the callback for permission requests on versions less than 10, where you use READ_EXTERNAL_STORAGE and WRITE_EXTERNAL_STORAGE.
     */
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String?>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode != 9894) return

        if (grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED)
            this.onPermissionsGranted()
        else
            this.onPermissionsRejected()
    }

    /**
     * The ending callback for once permissions are successfully granted, this renders the compose app.
     */
    private fun onPermissionsGranted() {
        setContent {
            ComposableFilesTheme {
                val navController = rememberNavController()

                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavHost(navController = navController, startDestination = "directory/local/${Environment.getExternalStorageDirectory().absolutePath.urlEncode()}") {
                        composable(
                            route = "directory/{fileSystem}/{path}",
                            arguments = listOf(
                                // A key that is parsed into an object (i.e. "local" -> LocalFileSystem)
                                navArgument("fileSystem") {
                                    type = NavType.StringType
                                },
                                // A url encoded path string
                                navArgument("path") {
                                    type = NavType.
                                }
                            )
                        ) { entry ->
                            DirectoryScreen(
                                path = entry.arguments!!.getString("path")!!,
                                // New file systems should add an entry here for navigation
                                fileSystem = when (entry.arguments!!.getString("fileSystem")!!) {
                                    "local" -> LocalFileSystem()
                                    else -> throw IllegalArgumentException("An invalid filesystem key was provided for navigation")
                                }
                            )
                        }
//                        composable("settings") { SettingsScreen(/*...*/) }
                    }

                }
            }
        }
    }

    /**
     * The ending callback for if permissions are rejected, this sends a toast error and then closes the app.
     */
    private fun onPermissionsRejected() {
        Toast.makeText(this, "This app requires external storage permissions to function.", Toast.LENGTH_LONG).show()
        this.finishAndRemoveTask()
    }
}