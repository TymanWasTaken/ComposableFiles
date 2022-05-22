package tech.tyman.composablefiles.ui.components

import android.os.Environment
import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.*
import androidx.compose.runtime.*
import tech.tyman.composablefiles.data.Folder
import tech.tyman.composablefiles.data.getParent
import tech.tyman.composablefiles.ui.components.files.FileList
import java.io.File as JavaFile

@Composable
fun Directory(path: String) {
    var folder by remember { mutableStateOf(
        Folder(JavaFile(path))
    ) }
    // There is likely a better way to do this, but I do not know how, so I just create another mutable state
    var files by remember { mutableStateOf(folder.files) }

    Column {
        DirectoryTopBar(
            folder = folder,
            onReload = {
                folder = Folder(JavaFile(folder.path))
                files = folder.files
            },
            onHome = {
                folder = Folder(JavaFile(Environment.getExternalStorageDirectory().absolutePath))
                files = folder.files
            }
        )

//        Spacer(modifier = Modifier.padding(8.dp))

        FileList(
            parent = folder.getParent(),
            files = files,
            onFileClick = {
                if (!it.isDirectory) return@FileList
                folder = Folder(it.javaFile)
                files = folder.files
            },
            onFileLongClick = {

            }
        )
    }
}