package tech.tyman.composablefiles

import android.Manifest
import android.os.Environment
import androidx.activity.ComponentActivity
import androidx.activity.result.contract.ActivityResultContracts

fun ComponentActivity.requestPermissions(permission: String, callback: (granted: Boolean) -> Unit) {
    if (Environment.isExternalStorageManager()) return callback(true)

    val launcher = registerForActivityResult(ActivityResultContracts.RequestPermission()) {
        callback(it)
    }
    launcher.launch(Manifest.permission.MANAGE_EXTERNAL_STORAGE)
}