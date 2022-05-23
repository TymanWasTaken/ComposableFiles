package tech.tyman.composablefiles.data.filesystems

import tech.tyman.composablefiles.data.FileSystem
import tech.tyman.composablefiles.data.FileSystemEntry
import java.io.File

class LocalFileSystem : FileSystem() {
    override fun readDir(path: String): List<FileSystemEntry> = File(path).listFiles()?.map {
        LocalFileSystemEntry(this, path)
    } ?: throw IllegalArgumentException("Path argument was invalid")

    override fun createDir(path: String, recursive: Boolean): Boolean =
        if (recursive) File(path).mkdirs()
        else File(path).mkdir()

    override fun deleteDir(path: String): Boolean = File(path).deleteRecursively()

    override fun getParent(path: String): LocalFileSystemEntry? {
        return LocalFileSystemEntry(this, File(path).parentFile?.absolutePath ?: return null)
    }

    override fun readFile(path: String): String = File(path).readText()

    override fun getFile(path: String) = LocalFileSystemEntry(this, path)

    override fun writeFile(path: String, data: String) = File(path).writeText(data)

    override fun deleteFile(path: String): Boolean = File(path).delete()
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