package tech.tyman.composablefiles.data

abstract class FileSystemEntry {
    abstract val fileSystem: FileSystem
    abstract val path: String
    abstract val name: String
    abstract val lastModified: Long
    abstract val isDirectory: Boolean

    fun readFile(): String = fileSystem.readFile(path)
    fun writeFile(data: String) = fileSystem.writeFile(path, data)
    fun deleteFile(): Boolean = fileSystem.deleteDir(path)

    fun readDir(): List<FileSystemEntry> = fileSystem.readDir(path)
    fun deleteDir(): Boolean = fileSystem.deleteDir(path)
    fun getParent() = fileSystem.getParent(path)

    fun delete(): Boolean = if (isDirectory) deleteDir() else deleteFile()
}