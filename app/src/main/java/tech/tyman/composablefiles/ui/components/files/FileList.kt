package tech.tyman.composablefiles.ui.components.files

import android.view.HapticFeedbackConstants
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.platform.LocalView
import tech.tyman.composablefiles.data.component.DirectoryEntry

@Composable
fun FileListComponent(
    parent: DirectoryEntry?,
    files: List<DirectoryEntry>,
    selectedFiles: SnapshotStateList<DirectoryEntry>,
    onFileClick: (file: DirectoryEntry) -> Unit,
    onFileSelect: (files: List<DirectoryEntry>) -> Unit
) {
    val cachedSelectedFileEntries by derivedStateOf { mutableStateListOf<DirectoryEntry>() }
    cachedSelectedFileEntries.addAll(selectedFiles)

    LazyColumn {
        items(
            if (parent != null) listOf(parent, *files.sortedBy { it.name }.toTypedArray())
            else files.sortedBy { it.name }
        ) { fileEntry ->
            val selected = cachedSelectedFileEntries.contains(fileEntry)
            val view = LocalView.current

            DirectoryEntryComponent(
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