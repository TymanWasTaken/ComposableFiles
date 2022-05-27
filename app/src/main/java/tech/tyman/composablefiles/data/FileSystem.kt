package tech.tyman.composablefiles.data

/**
 * An abstract class to implement another filesystem that can be used in the app (for example over the network)
 */
abstract class FileSystem {
    var loaded: Boolean = false

    open fun load() {
        loaded = true
    }
    open fun unload() {
        loaded = false
    }

    abstract fun getEntry(path: String): FileSystemEntry
}

/**
 * An enum containing all filesystem types, should be updated when filesystem types are added/removed
 */
enum class FileSystemType {
    LOCAL
}