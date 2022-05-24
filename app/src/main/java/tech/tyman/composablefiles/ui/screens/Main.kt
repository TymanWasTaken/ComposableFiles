package tech.tyman.composablefiles.ui.screens

import android.os.Environment
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import tech.tyman.composablefiles.data.filesystems.LocalFileSystem
import tech.tyman.composablefiles.data.filesystems.SftpFileSystem
import tech.tyman.composablefiles.ui.components.DirectoryComponent
import tech.tyman.composablefiles.utils.showToast

@Composable
fun MainScreen() {
    val context = LocalContext.current
    DirectoryComponent(
        path = Environment.getExternalStorageDirectory().absolutePath,
        SftpFileSystem(
            username = "tyman",
            host = "192.168.0.98",
            port = 22,
            onPasswordPrompt = { "LinuxIsCool7!" },
            onMessage = { context.showToast(it) },
            onConfirmationPrompt = { context.showToast(it); true },
        )
    )
}