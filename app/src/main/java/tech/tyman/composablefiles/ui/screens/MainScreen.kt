package tech.tyman.composablefiles.ui.screens

import android.os.Environment
import androidx.compose.runtime.Composable
import tech.tyman.composablefiles.data.File
import tech.tyman.composablefiles.data.Folder
import tech.tyman.composablefiles.ui.components.Directory
import tech.tyman.composablefiles.ui.components.FilesList
import java.nio.file.Paths
import java.io.File as JavaFile

@Composable
fun MainScreen() {
    val javaDir = JavaFile(
        Paths.get(Environment.getExternalStorageDirectory().absolutePath, "Download").toUri()
    )
    Directory(folder = Folder(javaDir))
}