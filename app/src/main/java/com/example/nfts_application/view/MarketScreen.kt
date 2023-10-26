package com.example.nfts_application.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowColumn
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Category
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.StackedBarChart
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.nfts_application.R
import com.example.nfts_application.component.ETHPriceText

@Composable
fun MarketScreen(navController: NavHostController){
    NavHost(navController = navController, startDestination = "marketHomeScreen"){
        composable("marketHomeScreen") {
            MarketHomeScreen(navController)
        }

        composable("NFTsDetail"){
            NFTsDetailsScreen()
        }
    }
}

@Composable
fun MarketHomeScreen(navController: NavHostController){
    var expanded by remember { mutableStateOf(false) }

    Column {
        OutlinedButton(
            onClick = {expanded = true},
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Transparent,
                contentColor = Color.White,
            )
        ){
            Row{
                Icon(Icons.Outlined.Category, contentDescription = "")
                Spacer(modifier = Modifier.padding(4.dp))
                Text("Category", fontSize = 20.sp)

                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ){
                    DropdownMenuItem(
                        text = { Text("Edit") },
                        onClick = { /* Handle edit! */ },
                        leadingIcon = {
                            Icon(
                                Icons.Outlined.Edit,
                                contentDescription = null
                            )
                        })
                }
            }
        }

        Spacer(modifier = Modifier.padding(8.dp))
        Divider(color=MaterialTheme.colorScheme.onSecondary)

        LazyColumn{
            items(5){
                MarketNFTCard(navController)
            }
        }
    }

}


@OptIn(ExperimentalLayoutApi::class)
@Composable
fun MarketNFTCard(navController: NavHostController){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { navController.navigate("NFTsDetail") },
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.secondary,
        ),
    ){
        Row(
            modifier = Modifier.padding(12.dp)
        ){
            Column (
                modifier = Modifier.fillMaxHeight()
            ){
                Image(
                    modifier = Modifier.size(100.dp),
                    painter = painterResource(id = R.drawable.picture_demo),
                    contentScale = ContentScale.Crop,
                    contentDescription = ""
                )
            }

            Spacer(modifier = Modifier.padding(8.dp))

            Column (
                modifier = Modifier
                    .fillMaxSize()
            ){
                
                Text("Travel Monkey Club", fontSize = 20.sp, color = Color.White, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.padding(18.dp))
                ETHPriceText(price = "0.8998000")
                
            }
        }
    }
    Spacer(modifier = Modifier.padding(8.dp))
}