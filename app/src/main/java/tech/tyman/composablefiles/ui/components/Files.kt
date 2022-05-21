package tech.tyman.composablefiles.ui.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import tech.tyman.composablefiles.data.File

@Composable
fun FilesList(files: List<File>) {
    LazyColumn(modifier = Modifier.padding(all = 8.dp)) {
        items(files) { file ->
            FileEntry(file)
        }
    }
}

@Composable
fun FileEntry(file: File) {
    Row(modifier = Modifier.padding(all = 8.dp)) {
//        AsyncImage(
//            model = msg.avatarUrl,
//            contentDescription = "${msg.author}'s avatar",
//            modifier = Modifier
//                .size(size = 40.dp)
//                .clip(shape = CircleShape)
//                .border(1.5.dp, MaterialTheme.colorScheme.primary, CircleShape)
//        )

//        Spacer(modifier = Modifier.padding(all = 8.dp))

        Column {
            Text(
                text = file.name,
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = file.conentInfo.contentType.simpleName,
                color = MaterialTheme.colorScheme.onSurface,
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}