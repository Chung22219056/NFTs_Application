package com.example.nfts_application.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import coil.compose.AsyncImage
import com.example.nfts_application.API_URL
import com.example.nfts_application.R
import com.example.nfts_application.getBalance
import com.example.nfts_application.getNFTs
import com.example.nfts_application.model.NFTs

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun HomeScreen(navController: NavHostController){
    var NFTsList by remember { mutableStateOf(listOf<NFTs>()) }

    LaunchedEffect(key1 = true) {
        NFTsList = getNFTs(5)
    }

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
                        items(NFTsList){ items ->
                            NFT_Card(navController, items)
                        }
                    }
                }

                item{
                    Spacer(modifier = Modifier.padding(12.dp))
                    Text("Top Seller", fontSize = 24.sp)
                }

                item{
                    LazyRow(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                    ){
                        items(3){
                            User_Card(navController)
                        }
                    }
                }
            }
        }

        composable("NFTsDetail/{nft_id}",){ backStackEntry ->
            backStackEntry.arguments?.getString("nft_id")?.let { NFTsDetailsScreen(it) }
        }

        composable("UserProfileScreen"){
            UserProfileScreen()
        }
    }




}

@Composable
fun NFT_Card(navController: NavController, nfts: NFTs){
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
            Column (
                modifier = Modifier
                    .fillMaxSize()
                    .width(180.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                AsyncImage(
                    model = "$API_URL/api/nft/imageURL?url="+nfts.url,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .height(170.dp)
                        .width(170.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .clickable {
                            navController.navigate("NFTsDetail/"+nfts.id)
                        },
                    alignment = Alignment.Center,
                )
            }
            Spacer(modifier = Modifier.padding(12.dp))

            Text(nfts.name, fontSize = 18.sp, fontWeight = FontWeight.Bold,maxLines = 1, overflow = TextOverflow.Ellipsis )
        }
    }
}


@Composable
fun User_Card(navController: NavHostController){
    ElevatedCard (
        modifier = Modifier.width(150.dp).clickable { navController.navigate("UserProfileScreen") },
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.secondary,
        ),
    ){
        Column (
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            AsyncImage(
                model = "https://vuljespaarpot.nl/wp-content/uploads/2021/12/nfts-non-fungible-tokens.jpeg",
                contentDescription = "avatar",
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape)                       // clip to the circle shape
                    .border(2.dp, Color.Transparent, CircleShape)
            )
            Spacer(modifier = Modifier.padding(8.dp))
            Text("ZO werken",fontWeight = FontWeight.Bold,maxLines = 1, overflow = TextOverflow.Ellipsis)
        }

    }
}

@Composable
fun TopSellerCard(){
    ElevatedCard (
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.secondary,
        ),
    ){


    }
}


//legacy
//@Composable
//fun NFT_Card(navController: NavController, nfts: NFTs){
//    ElevatedCard (
//        elevation = CardDefaults.cardElevation(
//            defaultElevation = 6.dp
//        ),
//        colors = CardDefaults.cardColors(
//            containerColor = MaterialTheme.colorScheme.secondary,
//        ),
//    ){
//        Column(
//            modifier = Modifier
//                .padding(12.dp)
//                .fillMaxSize()
//                .width(180.dp)
//        ){
//            Column (
//                modifier = Modifier
//                    .fillMaxSize()
//                    .width(180.dp),
//                horizontalAlignment = Alignment.CenterHorizontally
//            ){
//                Image(
//                    painter = painterResource(id = R.drawable.picture_demo),
//                    contentDescription = "",
//                    modifier = Modifier
//                        .width(170.dp)
//                        .height(170.dp)
//                        .clip(RoundedCornerShape(8.dp))
//                        .clickable {
//                            navController.navigate("NFTsDetail")
//                        },
//                )
////                AsyncImage(
////                    model = "$API_URL/api/API_URL?url="+nfts.url,
////                    contentDescription = null,
////                    contentScale = ContentScale.FillWidth,
////                    modifier = Modifier
////                        .height(150.dp)
////                        .width(150.dp),
////                    alignment = Alignment.Center
////                )
//            }
//            Spacer(modifier = Modifier.padding(12.dp))
//
//            Text("Travel Monkey Club", fontSize = 18.sp, fontWeight = FontWeight.Bold,maxLines = 1, overflow = TextOverflow.Ellipsis )
//        }
//    }
//}