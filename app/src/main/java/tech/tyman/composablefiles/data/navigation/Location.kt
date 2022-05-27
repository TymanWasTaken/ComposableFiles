package tech.tyman.composablefiles.data.navigation

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import tech.tyman.composablefiles.data.FileSystemType

@Parcelize
data class Location(
    val fileSystem: FileSystemType,
    val path: String
) : Parcelable