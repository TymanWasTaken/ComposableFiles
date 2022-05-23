package tech.tyman.composablefiles.data.component

import tech.tyman.composablefiles.data.FileSystem
import tech.tyman.composablefiles.data.FileSystemEntry

data class DirectoryInfo(
    val fileSystemEntry: FileSystemEntry
) {
    val files = fileSystemEntry.readDir()
    val path = fileSystemEntry.path
    val fileCount
        get() = files.size

    fun clone(newPath: String = path) = DirectoryInfo(fileSystemEntry.fileSystem.getFile(newPath))
    fun getParent(): DirectoryEntry = DirectoryEntry(fileSystemEntry.fileSystem, )
}
