package tech.tyman.composablefiles.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import tech.tyman.composablefiles.data.component.DirectoryInfo

@Composable
fun DirectoryTopBarComponent(
    title: String,
    directory: DirectoryInfo,
    onButton: (type: TopBarAction) -> Unit,
    buttonState: TopBarState
) {
    SmallTopAppBar(
        title = {
            Column {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleLarge
                )
                Text(
                    text = directory.path,
                    style = MaterialTheme.typography.titleSmall
                )
            }
        },
        navigationIcon = {
            IconButton(
                onClick = { onButton(TopBarAction.MENU) }
            ) {
                Icon(Icons.Filled.Menu, contentDescription = "Menu")
            }
        },
        actions = {
            TopBarButtons(buttonState, onButton)
        },
        colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.secondaryContainer,
            titleContentColor = MaterialTheme.colorScheme.onSecondaryContainer
        )
    )
}

enum class TopBarAction {
    HOME,
    RELOAD,
    DELETE,
    DONE_SELECTING,
    MENU
}

@Composable
fun TopBarButtons(state: TopBarState = TopBarState.DEFAULT, onButton: (type: TopBarAction) -> Unit) {
    when (state) {
        TopBarState.DEFAULT -> {
            // Home button (return to /storage/emulated/0)
            IconButton(onClick = { onButton(TopBarAction.HOME) }) {
                Icon(Icons.Filled.Home, contentDescription = "Home")
            }
            // Refresh button (reload files)
            IconButton(onClick = { onButton(TopBarAction.RELOAD) }) {
                Icon(Icons.Filled.Refresh, contentDescription = "Refresh")
            }
        }
        TopBarState.SELECTION -> {
            // Delete button (deletes selected files)
            IconButton(onClick = { onButton(TopBarAction.DELETE) }) {
                Icon(Icons.Filled.Delete, contentDescription = "Delete")
            }
            // Checkmark button (unselects all files)
            IconButton(onClick = { onButton(TopBarAction.DONE_SELECTING) }) {
                Icon(Icons.Filled.Check, contentDescription = "Checkmark")
            }
        }
    }
}

enum class TopBarState {
    DEFAULT,
    SELECTION
}