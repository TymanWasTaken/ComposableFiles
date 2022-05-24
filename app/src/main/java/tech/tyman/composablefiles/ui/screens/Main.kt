package tech.tyman.composablefiles.ui.screens

import android.os.Environment
import androidx.compose.runtime.Composable
import tech.tyman.composablefiles.data.filesystems.LocalFileSystem
import tech.tyman.composablefiles.ui.components.DirectoryComponent

@Composable
fun MainScreen() {
    DirectoryComponent(
        path = Environment.getExternalStorageDirectory().absolutePath,
        LocalFileSystem()
    )
}