package tech.tyman.composablefiles.data.filesystems

import tech.tyman.composablefiles.data.FileSystem
import tech.tyman.composablefiles.data.FileSystemEntry
import java.io.File

class LocalFileSystem : FileSystem() {
    override fun readDir(path: String): List<FileSystemEntry> {
        return File(path).listFiles().map {

        }
    }

    override fun createDir(path: String): Boolean {
        TODO("Not yet implemented")
    }

    override fun deleteDir(path: String): Boolean {
        TODO("Not yet implemented")
    }

}

class LocalFileSystemEntry(
    override val fileSystem: FileSystem,
    override val path: String
) : FileSystemEntry() {
    private val javaFile = File(path)
    override val name: String = javaFile.name
    override val lastModified: Long = javaFile.lastModified()
    override val isDirectory: Boolean = javaFile.isDirectory
}