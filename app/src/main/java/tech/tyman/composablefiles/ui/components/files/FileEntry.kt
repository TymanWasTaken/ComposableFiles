package tech.tyman.composablefiles.ui.components.files

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import tech.tyman.composablefiles.data.File
import tech.tyman.composablefiles.utils.iconInfo

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun FileEntry(file: File, onClick: () -> Unit, onLongClick: () -> Unit) {
    Row(
        modifier = Modifier
            .padding(all = 8.dp)
            .combinedClickable(
                onClick = onClick,
                onLongClick = onLongClick
            )
            .fillMaxWidth()
    ) {
        FileIcon(file)

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