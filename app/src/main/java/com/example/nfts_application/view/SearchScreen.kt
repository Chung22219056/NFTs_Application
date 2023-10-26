package com.example.nfts_application.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun SearchScreen(navController: NavHostController){
    NavHost(navController = navController, startDestination = "searchHomeScreen"){
        composable("searchHomeScreen") {
            SearchHomeScreen(navController)
        }

        composable("NFTsDetail"){
            NFTsDetailsScreen()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchHomeScreen(navController: NavHostController){
    var searchValue by remember { mutableStateOf("") }

    Column {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            leadingIcon = {Icon(Icons.Outlined.Search, contentDescription = "search")},
            value = searchValue,
            onValueChange = { searchValue = it },
            label = { Text("Search") },
            colors = TextFieldDefaults.textFieldColors(
                textColor = Color.White,
                containerColor = MaterialTheme.colorScheme.secondary,
                placeholderColor = MaterialTheme.colorScheme.onSecondary,
                focusedIndicatorColor = MaterialTheme.colorScheme.onPrimary,
                focusedLabelColor = MaterialTheme.colorScheme.onPrimary,
                focusedLeadingIconColor = MaterialTheme.colorScheme.onPrimary,


            )
        )

        Spacer(modifier = Modifier.padding(8.dp))
        Divider(color= MaterialTheme.colorScheme.onSecondary)
    }
}