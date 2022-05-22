package tech.tyman.composablefiles.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.*
import androidx.compose.runtime.*
import tech.tyman.composablefiles.data.Folder
import java.io.File as JavaFile

@Composable
fun Directory(path: String) {
    var folder by remember { mutableStateOf(
        Folder(JavaFile(path))
    ) }
    // There is likely a better way to do this, but I do not know how, so I just create another mutable state
    var files by remember { mutableStateOf(folder.files) }

    Column {
        SmallTopAppBar(
            title = {
                Column {
                    Text(
                        text = folder.name,
                        style = MaterialTheme.typography.titleLarge
                    )
                    Text(
                        text = folder.path,
                        style = MaterialTheme.typography.titleSmall
                    )
                }
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
                    val newFolder = Folder(JavaFile(path))
                    folder = newFolder
                    files = newFolder.files
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

        FilesList(files)
    }
}