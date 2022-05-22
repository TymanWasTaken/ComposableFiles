package tech.tyman.composablefiles.data

import android.webkit.MimeTypeMap
import java.io.File as JavaFile

data class File(
    val javaFile: JavaFile,
    val name: String = javaFile.name,
    val path: String = javaFile.absolutePath,
    val extension: String = javaFile.extension,
    val mimeType: String? = MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension)
) {
    val lastModified get() = javaFile.lastModified()
    val isDirectory get() = javaFile.isDirectory
    val isFile get() = javaFile.isFile
}

/**
 * Gets the parent File object for an open folder
 */
fun Folder.getParent(): File? {
    return File(
        javaFile = this.javaFile.parentFile ?: return null,
        name = ".."
    )
}