package tech.tyman.composablefiles.data

import java.io.File as JavaFile

data class Folder(
    private val file: JavaFile
) {
    val fileCount
        get() = files.count()
    val name: String = file.name
    val path: String = file.absolutePath
    val files = (file.listFiles() ?: arrayOf()).map { File(it) }

    init {
        require(file.isDirectory) { "file argument must be a directory" }
    }
}
