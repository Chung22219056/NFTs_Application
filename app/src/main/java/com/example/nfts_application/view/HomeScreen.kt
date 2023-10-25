package com.example.nfts_application.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.nfts_application.R

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun HomeScreen(navController: NavHostController){
    NavHost(navController = navController, startDestination = "home"){
        composable("home") {
            LazyColumn(
                contentPadding = PaddingValues(8.dp),
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(10.dp),
            ){

                item{
                    Text("Live Auction", fontSize = 24.sp)
                }

                item{
                    LazyRow(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                    ){
                        items(3){
                            NFT_Card(navController)
                        }
                    }
                }

                item{
                    Spacer(modifier = Modifier.padding(12.dp))
                    Text("Top Seller", fontSize = 24.sp)
                }
            }
        }

        composable("NFTsDetail"){
            NFTsDetailsScreen()
        }
    }




}

@Composable
fun NFT_Card(navController: NavController){
    ElevatedCard (
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.secondary,
        ),
    ){
        Column(
            modifier = Modifier
                .padding(12.dp)
                .fillMaxSize()
                .width(180.dp)
        ){
//                AsyncImage(
//                    model = "",
//                    contentDescription = null,
//                    contentScale = ContentScale.FillWidth,
//                    modifier = Modifier
//                        .height(150.dp)
//                        .width(150.dp),
//                    alignment = Alignment.Center
//                )
            Column (
                modifier = Modifier
                    .fillMaxSize()
                    .width(180.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Image(
                    painter = painterResource(id = R.drawable.picture_demo),
                    contentDescription = "",
                    modifier = Modifier
                        .width(170.dp)
                        .height(170.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .clickable {
                            navController.navigate("NFTsDetail")
                        },
                )
            }
            Spacer(modifier = Modifier.padding(12.dp))

            Text("Travel Monkey Club", fontSize = 18.sp, fontWeight = FontWeight.Bold,maxLines = 1, overflow = TextOverflow.Ellipsis )
        }
    }
}