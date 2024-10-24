package com.example.navigation_drawer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.rounded.AccountCircle
import androidx.compose.material.icons.rounded.Email
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.navigation_drawer.ui.theme.Navigation_DrawerTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Navigation_DrawerTheme {
                Screen()
            }
        }
    }
}

@Composable
fun Screen(modifier: Modifier = Modifier) {
    val drawerState = rememberDrawerState(
        initialValue = DrawerValue.Closed
    )
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                DrawerContent()
            }
        }
    ) {
        Scaffold(
            topBar = {
                 TopBar(
                     onOpenDrawer = {
                         scope.launch {
                             drawerState.apply {
                                 if (isClosed) open() else close()
                             }
                         }
                     })
            }
        ) { padding ->
            ScreenContent(modifier = Modifier.padding(padding))
        }
    }
}

@Composable
fun DrawerContent(modifier: Modifier = Modifier) {
    Text(
        text = "My App",
        fontSize = 24.sp,
        modifier = Modifier.padding(16.dp)
    )

    HorizontalDivider()

    Spacer(modifier = Modifier.height(5.dp))

    NavigationDrawerItem(
        icon = {
            Icon(
                imageVector = Icons.Rounded.AccountCircle,
                contentDescription = "Account",
                modifier = Modifier.size(24.dp)
            )
        },
        label = {
            Text(
                text = "Account",
                fontSize = 18.sp,
            )
        },
        selected = false,
        onClick = {}
    )

    Spacer(modifier = Modifier.height(3.dp))

    NavigationDrawerItem(
        icon = {
            Icon(
                imageVector = Icons.Rounded.Notifications,
                contentDescription = "Notifications",
                modifier = Modifier.size(24.dp)
            )
        },
        label = {
            Text(
                text = "Notifications",
                fontSize = 18.sp,
            )
        },
        selected = false,
        onClick = {}
    )
    Spacer(modifier = Modifier.height(3.dp))

    NavigationDrawerItem(
        icon = {
            Icon(
                imageVector = Icons.Rounded.Email,
                contentDescription = "Inbox",
                modifier = Modifier.size(24.dp)
            )
        },
        label = {
            Text(
                text = "Inbox",
                fontSize = 18.sp,
            )
        },
        selected = false,
        onClick = {}
    )
}

@Composable
fun ScreenContent(modifier: Modifier = Modifier) {
//   Add the contents of the screen here
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    onOpenDrawer: () -> Unit
) {
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor =  MaterialTheme.colorScheme.tertiaryContainer.copy(alpha = 0.6f),
        ),
        navigationIcon = {
            Icon(
                imageVector = Icons.Default.Menu,
                contentDescription = "Menu",
                modifier = Modifier
                    .padding(start = 16.dp, end = 15.dp)
                    .size(20.dp)
                    .clickable {
                    onOpenDrawer()
                }

            )
        },
        title = {
            Text(text = "My App")
        },
        actions = {
            Icon(
                imageVector = Icons.Default.Notifications,
                contentDescription = "Notofication",
                modifier = Modifier
                    .padding(start = 8.dp, end = 10.dp)
                    .size(25.dp)
            )
            Icon(
                imageVector = Icons.Default.AccountCircle,
                contentDescription = "Accounts",
                modifier = Modifier
                    .padding(start = 8.dp, end = 15.dp)
                    .size(25.dp)
            )
        }
    )
}