package tech.tyman.composablefiles.ui.components.files

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
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
import tech.tyman.composablefiles.data.FileEntry
import tech.tyman.composablefiles.utils.iconInfo

@Composable
fun FileIconComponent(file: FileEntry) {
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