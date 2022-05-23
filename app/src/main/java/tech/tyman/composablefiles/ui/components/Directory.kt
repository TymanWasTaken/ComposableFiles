package tech.tyman.composablefiles.ui.components

import android.os.Environment
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import tech.tyman.composablefiles.data.*
import tech.tyman.composablefiles.data.component.DirectoryInfo
import tech.tyman.composablefiles.ui.components.files.FileListComponent
import tech.tyman.composablefiles.utils.popAll
import tech.tyman.composablefiles.utils.replaceWith
import tech.tyman.composablefiles.utils.showToast

@Composable
fun DirectoryComponent(path: String, fileSystem: FileSystem) {
    var directory by remember { mutableStateOf(
        DirectoryInfo(fileSystem, path)
    ) }
    val selectedFiles = remember { mutableStateListOf<FileSystemEntry>() }
    // There is likely a better way to do this, but I do not know how, so I just create another mutable state
    var files by remember { mutableStateOf(directory.files) }

    val context = LocalContext.current

    Column {
        DirectoryTopBarComponent(
            title = if (selectedFiles.size > 0) selectedFiles.size.toString() else directory.name,
            directory = directory,
            onButton = {
                when (it) {
                    TopBarAction.HOME -> {
                        selectedFiles.clear()
                        directory = directory.clone(Environment.getExternalStorageDirectory().absolutePath)
                        files = directory.files
                    }
                    TopBarAction.RELOAD -> {
                        selectedFiles.clear()
                        directory = directory.clone()
                        files = directory.files
                    }
                    TopBarAction.DELETE -> {
                        val failed = mutableListOf<FileSystemEntry>()
                        selectedFiles.popAll { entry ->
                            if (!entry.delete()) failed.add(entry)
                        }
                        directory = directory.clone()
                        files = directory.files
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
            parent = directory.getParent(),
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