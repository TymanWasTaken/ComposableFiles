package tech.tyman.composablefiles.data

import android.webkit.MimeTypeMap
import androidx.compose.ui.graphics.vector.ImageVector
import tech.tyman.composablefiles.utils.getIconForExtension
import java.io.File

data class File(
    private val file: File
) {
    val name: String = file.name
    val path: String = file.absolutePath
    val extension: String = file.extension
    val mimeType: String? = MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension)
    val lastModified = file.lastModified()
    val iconInfo: Pair<ImageVector, String> get() = extension.getIconForExtension()
}