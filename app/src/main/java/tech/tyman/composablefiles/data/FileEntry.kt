package tech.tyman.composablefiles.data

import android.webkit.MimeTypeMap
import java.io.File as JavaFile

/**
 * A data class wrapping around [java.io.File], to be used in components
 */
data class FileEntry(
    /**
     * The internal [java.io.File] that is used in this wrapper class
     */
    val javaFile: JavaFile,
    /**
     * The name of this file
     */
    val name: String = javaFile.name,
    /**
     * The absolute path as a [String] of this file
     */
    val path: String = javaFile.absolutePath,
    /**
     * The extension (or empty if no extension) of this file
     */
    val extension: String = javaFile.extension,
    /**
     * The mime type of this file, detected from the extension, or null if unknown
     */
    val mimeType: String? = MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension),
) {
    /**
     * The timestamp of when this file was last modified
     */
    val lastModified get() = javaFile.lastModified()

    /**
     * Whether or not this class represents a directory
     */
    val isDirectory get() = javaFile.isDirectory

    /**
     * Whether or not this class represents a file
     */
    val isFile get() = javaFile.isFile

    /**
     * Deletes this file
     * TODO: Implement some sort of recovery for deleted files, probably using something like a recycling bin
     *
     * @return a [Boolean] denoting whether or not the deletion finished successfully
     */
    fun delete(): Boolean {
        return if (this.isDirectory) {
            javaFile.deleteRecursively()
        } else javaFile.delete()
    }
}

/**
 * Gets the parent File object for an open folder
 */
fun Folder.getParent(): FileEntry? {
    return FileEntry(
        javaFile = this.javaFile.parentFile ?: return null, // Get the parent, or return null if there is none
        name = ".." // Override the name to display as ".."
    )
}