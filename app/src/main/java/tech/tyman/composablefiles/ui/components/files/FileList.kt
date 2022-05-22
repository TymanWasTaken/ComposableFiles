package tech.tyman.composablefiles.ui.components.files

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import tech.tyman.composablefiles.data.File

@Composable
fun FileList(parent: File?, files: List<File>, onFileClick: (file: File) -> Unit, onFileLongClick: (file: File) -> Unit) {
    val displayedFiles = if (parent != null) listOf(parent, *files.toTypedArray()) else files
    LazyColumn(modifier = Modifier.padding(all = 8.dp)) {
        items(displayedFiles.sortedBy { it.extension }) { file ->
            FileEntry(
                file = file,
                onClick = { onFileClick(file) },
                onLongClick =  { onFileLongClick(file) }
            )
        }
    }
}