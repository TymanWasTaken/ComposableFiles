package tech.tyman.composablefiles.data.component

import android.webkit.MimeTypeMap
import tech.tyman.composablefiles.data.FileSystemEntry

data class DirectoryEntry(
    val fileSystemEntry: FileSystemEntry,
    val name: String = fileSystemEntry.name
) {
    val extension = fileSystemEntry.extension
    val path = fileSystemEntry.path
    val mimeType = MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension)
    val isDirectory = fileSystemEntry.isDirectory
    val lastModified = fileSystemEntry.lastModified

    fun delete(recursive: Boolean = false) = fileSystemEntry.delete(recursive)
}