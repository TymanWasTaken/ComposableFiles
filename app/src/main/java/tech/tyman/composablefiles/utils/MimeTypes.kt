package tech.tyman.composablefiles.utils

import android.webkit.MimeTypeMap
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector
import tech.tyman.composablefiles.data.File

/**
 *
 */
val File.iconInfo: Pair<ImageVector, String> get() {
    return if (this.isDirectory) { // Folders
        Icons.Filled.Folder to "Folder"
    } else if (extensionIconMap.containsKey(this.extension)) { // Check for overridden file extensions
        extensionIconMap[this.extension]!!
    } else { // Otherwise, check the mime type
        mimeTypeIconMap[
                MimeTypeMap.getSingleton().getMimeTypeFromExtension(this.extension)
        ] ?: (Icons.Filled.QuestionMark to "Unknown") // If an unrecognized mime type, resort to "Unknown"
    }
}

/**
 * A map of mime types to their respective icons and descriptions
 */
private val mimeTypeIconMap = mapOf(
    // Archive formats
    "application/gzip" to (Icons.Filled.FolderZip to "Archive"),
    "application/x-7z-compressed" to (Icons.Filled.FolderZip to "Archive"),
    "application/zip" to (Icons.Filled.FolderZip to "Archive"),
    "application/java-archive" to (Icons.Filled.FolderZip to "Archive"),
    "application/vnd.android.package-archive" to (Icons.Filled.Android to "Archive"),
    "application/rar" to (Icons.Filled.Android to "Archive"),
    // Documents
    "application/epub+zip" to (Icons.Filled.Book to "Epub Ebook"),
    "application/x-mobipocket-ebook" to (Icons.Filled.Book to "Mobi Ebook"),
    "application/pdf" to (Icons.Filled.PictureAsPdf to "Pdf"),
    "application/vnd.openxmlformats-officedocument.presentationml.presentation" to (Icons.Filled.TextSnippet to "Word document"),
    // Audio
    "audio/mpeg" to (Icons.Filled.AudioFile to "MP3 Audio"),
    "audio/x-wav" to (Icons.Filled.AudioFile to "WAV Audio"),
    // Text formats
    "text/plain" to (Icons.Filled.TextSnippet to "Text"),
    "text/x-java" to (Icons.Filled.Code to "Java source code"),
    "text/html" to (Icons.Filled.Html to "HTML"),
    "text/css" to (Icons.Filled.Css to "CSS"),
    "text/javascript" to (Icons.Filled.Javascript to "Javascript"),
    "application/json" to (Icons.Filled.Code to "JSON"),
    "application/ld+json" to (Icons.Filled.Code to "JSON+LD"),
    // Other
    "application/pgp-keys" to (Icons.Filled.Key to "PGP Keys"),
    "application/x-msdos-program" to (Icons.Filled.DesktopWindows to "Windows exe"),
    "application/x-apple-diskimage" to (Icons.Filled.InstallDesktop to "Mac installer")
)

/**
 * A map of extra file extensions without mime types to icons and descriptions
 */
private val extensionIconMap = mapOf(
    "schem" to (Icons.Filled.Games to "Minecraft schema"),
    "bsdiff" to (Icons.Filled.Difference to "Binary diff"),
    "jks" to (Icons.Filled.Key to "Java KeyStore"),
    "zsh" to (Icons.Filled.Terminal to "Zsh script"),
    "bundle" to (Icons.Filled.Javascript to "React native bundle")
)