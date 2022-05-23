package tech.tyman.composablefiles.data

/**
 * An abstract class to implement another filesystem that can be used in the app (for example over the network)
 */
abstract class FileSystem {
    abstract fun load()
    abstract fun getEntry(path: String): FileSystemEntry
}