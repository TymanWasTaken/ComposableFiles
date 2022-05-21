package tech.tyman.composablefiles.ui.screens

import android.os.Environment
import androidx.compose.runtime.Composable
import tech.tyman.composablefiles.data.File
import tech.tyman.composablefiles.ui.components.FilesList
import java.nio.file.Paths
import java.io.File as JavaFile

@Composable
fun MainScreen() {
    val files = JavaFile(
        Paths.get(Environment.getExternalStorageDirectory().absolutePath, "Download").toUri()
    )
    FilesList(
        files.listFiles { file ->
            file.isFile
        }?.map { File(it) }?.toList() ?: listOf()
    )
}