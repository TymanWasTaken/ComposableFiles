package tech.tyman.composablefiles.data

import java.io.File as JavaFile

data class Folder(
    val javaFile: JavaFile,
    val name: String = javaFile.name,
    val path: String = javaFile.absolutePath,
    val files: List<File> = (javaFile.listFiles() ?: arrayOf()).map { File(it) }
) {
    val fileCount
        get() = files.count()

    init {
        require(javaFile.isDirectory) { "file argument must be a directory" }
    }
}
