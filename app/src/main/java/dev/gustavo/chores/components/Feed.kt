package dev.gustavo.chores.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.materialIcon
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import dev.gustavo.chores.R
import kotlinx.coroutines.launch

@Composable
fun FeedScreen(navController: NavController) {

    // Creates the navigation State and a coroutine scope to interact with the Drawer State
    val navDrawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    //lambda to change the state of the navigation drawer
    val onToggleNavigationDrawer: () -> Unit = {
        scope.launch { navDrawerState.open() }
    }

    ModalDrawer(
        drawerContent = { InnerDrawerContent() },
        drawerState = navDrawerState
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
        ) {
            NavigationBar(onToggleNavigationDrawer)
        }
    }
}

// A Header that will hold the Button to travel to the settings
// Please transfer the hard coded string to the string resources
@Composable
fun DrawerHeader(handleNavigateToSettings: () -> Unit) {
    Row {
        IconButton(onClick = handleNavigateToSettings) {
            Icon(
                imageVector = Icons.Rounded.Settings,
                contentDescription = "Settings Link",
                tint = MaterialTheme.colors.primary
            )
        }
    }
}

@Composable
fun GroupsList() {

}

// A Component to abstract away the hierarchy of the content inside the Drawer
@Composable
fun InnerDrawerContent() {
    Column {
        DrawerHeader({})
        GroupsList()
    }
}

// Please transfer the hard coded string to the string resources
@Composable
fun NavigationBar(onToggleNavigationDrawer: () -> Unit) {
    TopAppBar(
        title = { Text(text = stringResource(R.string.all_tasks)) },
        navigationIcon = {
            IconButton(onClick = onToggleNavigationDrawer, ) {
                Icon(
                    imageVector = Icons.Rounded.Menu,
                    contentDescription = "Menu Icon",
                    tint = MaterialTheme.colors.onSurface
                )
            }
        }
    )
}
