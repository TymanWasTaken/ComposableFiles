package tech.tyman.composablefiles.data

abstract class FileSystemEntry {
    abstract val fileSystem: FileSystem
    abstract val path: String
    abstract val name: String
    abstract val lastModified: Long
    abstract val isDirectory: Boolean

    abstract fun readFile(): String
    abstract fun writeFile(data: String): Boolean
    abstract fun deleteFile(): Boolean

    abstract fun readDir(): List<FileSystemEntry>
    abstract fun deleteDir(): Boolean
}