package com.example.nfts_application

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.PersonPin
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Shop
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.nfts_application.ui.theme.NFTs_ApplicationTheme
import com.example.nfts_application.view.HomeScreen
import com.example.nfts_application.view.MarketScreen
import com.example.nfts_application.view.NFTsDetailsScreen
import com.example.nfts_application.view.ProfileScreen
import kotlinx.coroutines.launch


data class BottomNavItem(val title: String, val icon: ImageVector){
    companion object{
        val data = listOf(
            BottomNavItem("Home", Icons.Filled.Home),
            BottomNavItem("Market", Icons.Filled.ShoppingCart),
            BottomNavItem("Search", Icons.Filled.Search),
            BottomNavItem("Profile", Icons.Filled.PersonPin),
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScaffoldScreen() {

    val navController = rememberNavController()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    var selectedItem by remember { mutableStateOf(0) }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                Text("Drawer title", modifier = Modifier.padding(16.dp))
                NavigationDrawerItem(
                    label = { Text(text = "Drawer Item") },
                    selected = false,
                    onClick = { /*TODO*/ },
                )
                // ...other drawer items
            }
        },
    ){
        Scaffold(
            topBar = {
                TopAppBar(
                    colors = TopAppBarDefaults.largeTopAppBarColors(
                        containerColor = MaterialTheme.colorScheme.background,
                        titleContentColor = MaterialTheme.colorScheme.onBackground,
                    ),
                    title = {
                        Image(
                            painter = painterResource(id = R.drawable.logo),
                            contentDescription = "",
                            modifier = Modifier.size(110.dp)
                        )
                    },
                    actions = {
                        IconButton(onClick = {
                            scope.launch {
                                drawerState.apply {
                                    if (isClosed) open() else close()
                                }
                            }
                        }) {
                            Icon(
                                imageVector = Icons.Filled.Menu,
                                contentDescription = "Localized description"
                            )
                        }
                    },
                    navigationIcon = {
                        val navBackStackEntry by navController.currentBackStackEntryAsState()

                        if (navBackStackEntry?.arguments?.getBoolean("topLevel") == false){
                            IconButton(
                                onClick = {navController.navigateUp()}
                            ) {
                                Icon( imageVector = Icons.Filled.ArrowBack,
                                    contentDescription = "Back", tint = Color.White)
                            }
                        } else{
                            null
                        }
                    }
                )
            },

            bottomBar = {
                val colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = MaterialTheme.colorScheme.onPrimary,
                    unselectedIconColor = Color.White,
                    unselectedTextColor = Color.White,
                    selectedTextColor = MaterialTheme.colorScheme.onPrimary,
                    indicatorColor = Color.Transparent
                )

                NavigationBar (
                    containerColor = MaterialTheme.colorScheme.background,
                    contentColor = Color.White
                ){
                    BottomNavItem.data.forEachIndexed{ index, bottomNavItem ->
                        NavigationBarItem(
                            colors = colors,
                            selected = selectedItem == index,
                            onClick = { selectedItem = index },
                            icon = { Icon(bottomNavItem.icon, contentDescription = bottomNavItem.title) },
                            label = { Text(bottomNavItem.title) }
                        )
                    }

                }
            }

        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding),
                verticalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                when(selectedItem){
                    0 -> HomeScreen(navController)
                    1 -> MarketScreen(navController)
                    3 -> ProfileScreen(navController)
                }
            }
        }
    }

}



@Preview(showBackground = true)
@Composable
fun ScaffoldPreview() {
    NFTs_ApplicationTheme {
        ScaffoldScreen()
    }
}