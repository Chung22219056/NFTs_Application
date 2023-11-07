package com.example.nfts_application.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun MoreScreen(navController: NavHostController){
    NavHost(navController = navController, startDestination = "Menu"){
        composable("Menu"){
            MoreScreenMenu(navController)
        }

        composable("EditProfileScreen"){

        }
    }
}
@Composable
fun MoreScreenMenu(navController: NavHostController){
    LazyColumn(

    ){
        item{
            Text("Profile Setting", fontSize = 24.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.padding(8.dp))
            OptionItem("Edit Profile")
        }

    }
}
@Composable
fun OptionItem(title: String){
    Box (
        modifier = Modifier
            .fillMaxWidth()
            .clickable { }
    ){
        Column {
            Spacer(modifier = Modifier.padding(8.dp))
            Text(title, fontSize = 20.sp)
            Spacer(modifier = Modifier.padding(8.dp))
        }
    }
    Divider(color = MaterialTheme.colorScheme.secondary)
}
