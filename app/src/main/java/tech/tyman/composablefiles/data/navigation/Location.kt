package tech.tyman.composablefiles.data.navigation

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import tech.tyman.composablefiles.data.FileSystemType
import tech.tyman.composablefiles.utils.urlEncode

@Parcelize
@Serializable
data class Location(
    val fileSystem: FileSystemType,
    val path: String
) : Parcelable {
    val navUrl: String
        get() = "directory/${Json.encodeToString(this).urlEncode()}"
}