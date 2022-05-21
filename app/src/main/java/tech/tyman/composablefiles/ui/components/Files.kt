package tech.tyman.composablefiles.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import coil.decode.SvgDecoder
import coil.decode.VideoFrameDecoder
import tech.tyman.composablefiles.data.File

@Composable
fun FilesList(files: List<File>) {
    LazyColumn(modifier = Modifier.padding(all = 8.dp)) {
        items(files.sortedBy { it.extension }) { file ->
            FileEntry(file)
        }
    }
}

@Composable
fun FileEntry(file: File) {
    Row(modifier = Modifier.padding(all = 8.dp)) {
        FileIcon(file)

        Column {
            Text(
                text = file.name,
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = file.mimeType ?: "Unknown mime type",
                color = MaterialTheme.colorScheme.onSurface,
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}

@Composable
fun FileIcon(file: File) {
    // Create image loader that supports images, svgs, gifs, and videos
    val imageLoader = ImageLoader.Builder(LocalContext.current)
        .components {
            add(ImageDecoderDecoder.Factory())
            add(SvgDecoder.Factory())
            add(GifDecoder.Factory())
            add(VideoFrameDecoder.Factory())
        }
        .build()

    // Load image and video files with coil
    val mimeType = file.mimeType ?: ""
    if (mimeType.startsWith("image/") || mimeType.startsWith("video/")) {
        AsyncImage(
            model = file.path,
            contentDescription = file.name,
            modifier = Modifier
                .size(size = 40.dp)
                .clip(shape = RoundedCornerShape(8.dp)),
            imageLoader = imageLoader
        )

    } else {
        val (icon, description) = file.iconInfo
        Icon(
            icon,
            contentDescription = description,
            modifier = Modifier
                .size(40.dp)
                .clip(shape = RoundedCornerShape(8.dp))
        )
    }

    Spacer(modifier = Modifier.padding(all = 8.dp))
}