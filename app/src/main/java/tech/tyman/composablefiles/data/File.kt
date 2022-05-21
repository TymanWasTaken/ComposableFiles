package tech.tyman.composablefiles.data

import com.j256.simplemagic.ContentInfo
import com.j256.simplemagic.ContentInfoUtil
import java.io.File

val magic = ContentInfoUtil()

data class File(
    private val file: File
) {
    val name: String = file.name
    val path: String = file.absolutePath
    val extension: String = file.extension
    val conentInfo: ContentInfo = magic.findMatch(file)
    val lastModified = file.lastModified()
}
