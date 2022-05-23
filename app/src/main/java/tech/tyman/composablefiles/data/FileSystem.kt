package tech.tyman.composablefiles.data

/**
 * An abstract class to implement another filesystem that can be used in the app (for example over the network)
 */
abstract class FileSystem {
    abstract fun readDir(path: String): List<FileSystemEntry>
    abstract fun createDir(path: String): Boolean
    abstract fun deleteDir(path: String): Boolean
}