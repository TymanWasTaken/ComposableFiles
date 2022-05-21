package tech.tyman.composablefiles.data

import android.webkit.MimeTypeMap
import java.io.File as JavaFile

data class File(
    private val file: JavaFile
) {
    val name: String = file.name
    val path: String = file.absolutePath
    val extension: String = file.extension
    val mimeType: String? = MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension)
    val lastModified get() = file.lastModified()
    val isDirectory get() = file.isDirectory
    val isFile get() = file.isFile
}