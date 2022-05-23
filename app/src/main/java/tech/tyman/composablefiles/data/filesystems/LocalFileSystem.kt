package tech.tyman.composablefiles.data.filesystems

import tech.tyman.composablefiles.data.FileSystem
import tech.tyman.composablefiles.data.FileSystemEntry
import java.io.File

class LocalFileSystem : FileSystem() {
    override fun getEntry(path: String) = LocalFileSystemEntry(this, File(path))
    override fun load() = Unit
}

class LocalFileSystemEntry(
    override val fileSystem: FileSystem,
    private val javaFile: File
) : FileSystemEntry() {
    override val path: String = javaFile.absolutePath
    override val name: String = javaFile.name
    override val extension: String = javaFile.extension
    override val lastModified: Long = javaFile.lastModified()
    override val isDirectory: Boolean = javaFile.isDirectory

    override fun readString(): String = javaFile.readText()

    override fun writeString(data: String): Boolean {
        javaFile.writeText(data)
        return true
    }

    override fun delete(recursive: Boolean): Boolean = if (recursive) javaFile.deleteRecursively() else javaFile.delete()

    override fun listFiles(): List<LocalFileSystemEntry>? = javaFile.listFiles()?.map { LocalFileSystemEntry(fileSystem, it) }

    override fun getParent(): LocalFileSystemEntry? {
        return LocalFileSystemEntry(fileSystem, javaFile.parentFile ?: return null)
    }
}