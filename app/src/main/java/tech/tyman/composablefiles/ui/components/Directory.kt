package tech.tyman.composablefiles.ui.components

import android.os.Environment
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import tech.tyman.composablefiles.data.FileEntry
import tech.tyman.composablefiles.data.Folder
import tech.tyman.composablefiles.data.getParent
import tech.tyman.composablefiles.ui.components.files.FileListComponent
import tech.tyman.composablefiles.utils.popAll
import tech.tyman.composablefiles.utils.replaceWith
import tech.tyman.composablefiles.utils.showToast
import java.io.File as JavaFile

@Composable
fun DirectoryComponent(path: String) {
    var folder by remember { mutableStateOf(
        Folder(JavaFile(path))
    ) }
    val selectedFiles = remember { mutableStateListOf<FileEntry>() }
    // There is likely a better way to do this, but I do not know how, so I just create another mutable state
    var files by remember { mutableStateOf(folder.files) }

    val context = LocalContext.current

    Column {
        DirectoryTopBarComponent(
            title = if (selectedFiles.size > 0) selectedFiles.size.toString() else folder.name,
            folder = folder,
            onButton = {
                when (it) {
                    TopBarAction.HOME -> {
                        selectedFiles.clear()
                        folder = folder.clone(path = Environment.getExternalStorageDirectory().absolutePath)
                        files = folder.files
                    }
                    TopBarAction.RELOAD -> {
                        selectedFiles.clear()
                        folder = folder.clone()
                        files = folder.files
                    }
                    TopBarAction.DELETE -> {
                        val failed = mutableListOf<FileEntry>()
                        selectedFiles.popAll { file ->
                            if (!file.delete()) failed.add(file)
                        }
                        folder = folder.clone()
                        files = folder.files
                        if (failed.size > 0) context.showToast("Failed to delete some files: ${
                            failed.joinToString(", ") { f -> f.name }
                        }")
                        else context.showToast("Successfully deleted files!")
                    }
                    TopBarAction.DONE_SELECTING -> {
                        selectedFiles.clear()
                    }
                    TopBarAction.MENU -> {
                        // TODO
                    }
                }
            },
            buttonState = if (selectedFiles.size > 0) TopBarState.SELECTION else TopBarState.DEFAULT
        )

        FileListComponent(
            parent = folder.getParent(),
            files = files,
            selectedFiles = selectedFiles,
            onFileClick = {
                if (!it.isDirectory) return@FileListComponent
                selectedFiles.clear()
                folder = Folder(it.javaFile)
                files = folder.files
            },
            onFileSelect = { selectedFiles.replaceWith(it) }
        )
    }
}