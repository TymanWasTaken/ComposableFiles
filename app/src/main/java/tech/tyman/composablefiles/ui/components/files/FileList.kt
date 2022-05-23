package tech.tyman.composablefiles.ui.components.files

import android.content.Context
import android.os.Build
import android.os.Build.VERSION.SDK_INT
import android.view.HapticFeedbackConstants
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import tech.tyman.composablefiles.data.FileEntry

@Composable
fun FileListComponent(
    parent: FileEntry?,
    files: List<FileEntry>,
    selectedFiles: SnapshotStateList<FileEntry>,
    onFileClick: (file: FileEntry) -> Unit,
    onFileSelect: (files: List<FileEntry>) -> Unit
) {
    val displayedFiles = if (parent != null) listOf(parent, *files.toTypedArray()) else files
    val cachedSelectedFileEntries by derivedStateOf { mutableStateListOf<FileEntry>() }
    cachedSelectedFileEntries.addAll(selectedFiles)

    val context = LocalContext.current

    LazyColumn {
        items(displayedFiles.sortedBy { it.extension }) { fileEntry ->
            val selected = cachedSelectedFileEntries.contains(fileEntry)
            val view = LocalView.current

            FileEntryComponent(
                file = fileEntry,
                onClick = {
                    if (cachedSelectedFileEntries.size > 0) {
                        when (selected) {
                            true -> cachedSelectedFileEntries.remove(fileEntry)
                            false -> cachedSelectedFileEntries.add(fileEntry)
                        }
                        onFileSelect(cachedSelectedFileEntries)
                    } else onFileClick(fileEntry)
                },
                onLongClick = {
                    view.performHapticFeedback(HapticFeedbackConstants.LONG_PRESS)
                    when (selected) {
                        true -> cachedSelectedFileEntries.remove(fileEntry)
                        false -> cachedSelectedFileEntries.add(fileEntry)
                    }
                    onFileSelect(cachedSelectedFileEntries)
                },
                selected = cachedSelectedFileEntries.contains(fileEntry)
            )
        }
    }
}