package tech.tyman.composablefiles.data

import java.io.File as JavaFile

data class Folder(
    private val file: JavaFile
) {
    val fileCount
        get() = files?.count() ?: 0
    val name: String = file.name
    val path = file.absolutePath

    var files: List<File>? = null
        private set

    init {
        require(file.isDirectory) {
            "file argument must be a directory"
        }
    }

    fun loadFiles() {
        files = file.listFiles()?.map { File(it) }?.toList()
    }
}
