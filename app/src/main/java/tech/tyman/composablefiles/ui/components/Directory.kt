package tech.tyman.composablefiles.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import tech.tyman.composablefiles.data.File
import tech.tyman.composablefiles.data.Folder

fun <T> SnapshotStateList<T>.updateList(newList: List<T>){
    clear()
    addAll(newList)
}

@Composable
fun Directory(folder: Folder) {
    val filesList = remember { mutableStateListOf<File>() }
    folder.loadFiles()
    filesList.updateList(folder.files!!)

    Column {
        SmallTopAppBar(
            title = {
                Text(folder.name)
            },
            navigationIcon = {
                IconButton(
                    onClick = {}
                ) {
                    Icon(Icons.Filled.Menu, contentDescription = "Menu")
                }
            },
            actions = {
                IconButton(onClick = {
                    folder.loadFiles()
                    filesList.updateList(folder.files!!)
                }) {
                    Icon(Icons.Filled.Refresh, contentDescription = "Refresh")
                }
            },
            colors = TopAppBarDefaults.smallTopAppBarColors(
                containerColor = MaterialTheme.colorScheme.secondaryContainer,
                titleContentColor = MaterialTheme.colorScheme.onSecondaryContainer
            )
        )

//        Spacer(modifier = Modifier.padding(8.dp))

        FilesList(filesList)
    }
}