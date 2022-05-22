package tech.tyman.composablefiles.data

import java.io.File as JavaFile

/**
 * A data class wrapping around java.io.File that stores a list of [FileEntry]s inside of it, to be used in components
 */
data class Folder(
    /**
     * The internal [java.io.File] used in this wrapper class
     */
    val javaFile: JavaFile,
    /**
     * The name of this folder
     */
    val name: String = javaFile.name,
    /**
     * The absoulute path of this folder
     */
    val path: String = javaFile.absolutePath,
    /**
     * The list of [FileEntry]s contained in this folder
     */
    val files: List<FileEntry> = (javaFile.listFiles() ?: arrayOf()).map { FileEntry(it) }
) {
    /**
     * The number of files loaded in this folder
     */
    val fileCount
        get() = files.count()

    init {
        require(javaFile.isDirectory) { "file argument must be a directory" }
    }

    /**
     * A simple utility method to clone this folder, optionally with a different path
     */
    fun clone(path: String = javaFile.absolutePath) = Folder(JavaFile(path))
}
