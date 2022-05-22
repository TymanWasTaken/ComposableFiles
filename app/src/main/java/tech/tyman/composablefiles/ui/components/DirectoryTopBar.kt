package tech.tyman.composablefiles.ui.components

import android.os.Environment
import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import tech.tyman.composablefiles.data.Folder

@Composable
fun DirectoryTopBar(folder: Folder, onReload: () -> Unit, onHome: () -> Unit) {
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
            // Home button (return to /storage/emulated/0)
            IconButton(onClick = onHome) {
                Icon(Icons.Filled.Home, contentDescription = "Home")
            }
            // Refresh button (reload files)
            IconButton(onClick = onReload) {
                Icon(Icons.Filled.Refresh, contentDescription = "Refresh")
            }
        },
        colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.secondaryContainer,
            titleContentColor = MaterialTheme.colorScheme.onSecondaryContainer
        )
    )
}