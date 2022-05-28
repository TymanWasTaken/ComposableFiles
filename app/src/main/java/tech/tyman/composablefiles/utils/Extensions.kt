package tech.tyman.composablefiles.utils

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.snapshots.SnapshotStateList
import java.net.URLEncoder

/**
 * Utility extension function to replace an entire [SnapshotStateList] with one function
 */
fun <T> SnapshotStateList<T>.replaceWith(list: List<T>) {
    this.clear()
    this.addAll(list)
}

/**
 * A version of .forEach for [MutableList]s that clears the list after iterating is done
 */
inline fun <T> MutableList<T>.popAll(action: (T) -> Unit) {
    for (element in this) action(element)
    this.clear()
}

/**
 * Simple utility extension function to show a toast on a [Context]
 */
fun Context.showToast(text: String, long: Boolean = false) {
    Toast.makeText(this, text, if (long) Toast.LENGTH_LONG else Toast.LENGTH_SHORT).show()
}

/**
 * Extension function to URL encode a string
 */
fun String.urlEncode(): String {
    return URLEncoder.encode(this, Charsets.UTF_8.toString())
}