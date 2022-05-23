package tech.tyman.composablefiles.data

/**
 * An abstract class to implement another filesystem that can be used in the app (for example over the network)
 */
abstract class FileSystem {
    abstract fun readDir(path: String): List<FileSystemEntry>
    abstract fun createDir(path: String, recursive: Boolean = false): Boolean
    abstract fun deleteDir(path: String): Boolean
    abstract fun getParent(path: String): FileSystemEntry?

    abstract fun readFile(path: String): String
    abstract fun getFile(path: String): FileSystemEntry
    abstract fun writeFile(path: String, data: String)
    abstract fun deleteFile(path: String): Boolean
}