package com.example.nfts_application.view

import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.UploadFile
import androidx.compose.material.icons.filled.Wallet
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
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
            EditProfileScreen()
        }

        composable("BalanceScreen"){
            BalanceScreen()
        }

        composable("CreateNFTsScreen"){
            CreateNFTsScreen()
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
            OptionItem("Edit Profile", Icons.Filled.Edit) { navController.navigate("EditProfileScreen") }
            OptionItem("Balance", Icons.Filled.Wallet) { navController.navigate("BalanceScreen") }
            Spacer(modifier = Modifier.padding(12.dp))
        }

        item{
            Text("My NFTs", fontSize = 24.sp, fontWeight = FontWeight.Bold)
            OptionItem("Create NFTs", Icons.Filled.UploadFile) { navController.navigate("CreateNFTsScreen") }
        }

    }
}
@Composable
fun OptionItem(title: String, icon: ImageVector, onclick: () -> Unit){
    Box (
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onclick() }
    ){
        Column {
            Spacer(modifier = Modifier.padding(8.dp))
            Row{
                Icon(icon, "")
                Spacer(modifier = Modifier.padding(8.dp))
                Text(title, fontSize = 20.sp)
            }

            Spacer(modifier = Modifier.padding(8.dp))
        }
    }
    Divider(color = MaterialTheme.colorScheme.secondary)
}

@Composable
fun EditProfileScreen(){
    var pickupIconImage by remember { mutableStateOf<Uri?>(null) }
    var pickupBackdropImage by remember { mutableStateOf<Uri?>(null) }

    LazyColumn(
    ){
        item {
            
            Button(
                onClick = { /*TODO*/ }
            ) {
                Text("Save", color = Color.White, fontSize = 20.sp)
            }
        }
    }
}
