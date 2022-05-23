package tech.tyman.composablefiles.data

abstract class FileSystemEntry {
    abstract val fileSystem: FileSystem
    abstract val path: String
    abstract val name: String
    abstract val extension: String
    abstract val lastModified: Long
    abstract val isDirectory: Boolean

    abstract fun readString(): String?
    abstract fun writeString(data: String): Boolean
    abstract fun delete(recursive: Boolean = false): Boolean
    abstract fun listFiles(): List<FileSystemEntry>?
    abstract fun getParent(): FileSystemEntry?
}