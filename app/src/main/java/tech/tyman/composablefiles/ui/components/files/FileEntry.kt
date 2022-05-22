package tech.tyman.composablefiles.ui.components.files

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import tech.tyman.composablefiles.data.FileEntry
import tech.tyman.composablefiles.utils.iconInfo

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun FileEntryComponent(file: FileEntry, onClick: () -> Unit, selected: Boolean, onFileSelect: () -> Unit, onFileUnselect: () -> Unit) {
    val selectedState by derivedStateOf { selected }

    Row(
        modifier = Modifier
            .combinedClickable(
                onClick = onClick,
                onLongClick = {
                    when (selectedState) {
                        true -> onFileUnselect()
                        false -> onFileSelect()
                    }
                }
            )
            .fillMaxWidth()
            .background(
                when (selectedState) {
                    true -> MaterialTheme.colorScheme.primaryContainer
                    false -> MaterialTheme.colorScheme.background
                }
            )
            .padding(8.dp)
    ) {
        FileIconComponent(file)

        Column {
            Text(
                text = file.name,
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = file.iconInfo.second,
                color = MaterialTheme.colorScheme.onSurface,
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}