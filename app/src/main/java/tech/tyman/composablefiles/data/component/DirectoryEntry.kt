package tech.tyman.composablefiles.data.component

import tech.tyman.composablefiles.data.FileSystem

data class DirectoryEntry(
    val fileSystem: FileSystem,
    val path: String
) {
    val fileSystemEntry = fileSystem.getFile(path)
    val name = fileSystemEntry.name
    val isDirectory = fileSystemEntry.isDirectory
    val lastModified = fileSystemEntry.lastModified
}