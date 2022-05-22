package tech.tyman.composablefiles.ui.components.files

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList
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

    LazyColumn {
        items(displayedFiles.sortedBy { it.extension }) { fileEntry ->
            FileEntryComponent(
                file = fileEntry,
                onClick = { onFileClick(fileEntry) },
                onFileSelect = {
                    cachedSelectedFileEntries.add(fileEntry)
                    onFileSelect(cachedSelectedFileEntries)
                },
                onFileUnselect = {
                    cachedSelectedFileEntries.remove(fileEntry)
                    onFileSelect(cachedSelectedFileEntries)
                },
                selected = cachedSelectedFileEntries.contains(fileEntry)
            )
        }
    }
}