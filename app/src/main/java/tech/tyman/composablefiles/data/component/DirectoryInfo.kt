package tech.tyman.composablefiles.data.component

import tech.tyman.composablefiles.data.FileSystemEntry
import java.io.IOException

data class DirectoryInfo(
    val fileSystemEntry: FileSystemEntry
) {
    val files = fileSystemEntry.listFiles()?.map { DirectoryEntry(it) } ?: throw IOException("Unable to read files")
    val path = fileSystemEntry.path
    val name = fileSystemEntry.name
    val fileCount
        get() = files.size

    fun clone(newPath: String = path) = DirectoryInfo(fileSystemEntry.fileSystem.getEntry(newPath))
    fun getParent(): DirectoryEntry? {
        return DirectoryEntry(fileSystemEntry.getParent() ?: return null, "..")
    }
}
