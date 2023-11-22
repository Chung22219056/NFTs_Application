package com.example.nfts_application.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.nfts_application.LoginResponse
import com.example.nfts_application.R
import com.example.nfts_application.component.ETHPriceText
import com.example.nfts_application.getNFTDetails
import com.example.nfts_application.getNFTs
import com.example.nfts_application.model.NFTs

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun NFTsDetailsScreen(nft_id: String){
    var nftDetails by remember { mutableStateOf(NFTs(0, "", "", "", 0.0, "")) }

    LaunchedEffect(key1 = true) {
        nftDetails = getNFTDetails(nft_id)
    }

    val openAlertDialogBuyNFTs = remember { mutableStateOf(false) }

    LazyColumn(

    ){
        item{
            BuyNFTsConfirmationAlertDialog(openAlertDialogBuyNFTs)
        }

        item{
            AsyncImage(
                model = nftDetails.getImageURL(),
                contentDescription = nftDetails.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(16.dp))
        }

        item{
            Text(nftDetails.name,fontSize = 28.sp, fontWeight = FontWeight.Bold)
            
            Spacer(modifier = Modifier.padding(8.dp))

            Text("Description", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.onSecondary)
            Spacer(modifier = Modifier.padding(2.dp))
            Text(nftDetails.description)

            Spacer(modifier = Modifier.padding(10.dp))

            FlowRow(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxSize()
            ) {
                Column{
                    Text("Current Price", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.onSecondary)
                    Spacer(modifier = Modifier.padding(2.dp))
                    ETHPriceText(nftDetails.price.toString())
                }

                Column{
                    Text("Created By", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.onSecondary)
                    Spacer(modifier = Modifier.padding(2.dp))
                    Text("Trista Francis")
                }
            }

            Spacer(modifier = Modifier.padding(8.dp))

            if(LoginResponse.loggedUser != null){ //only logged user can buy
                Button(onClick = { openAlertDialogBuyNFTs.value = true }) {
                    Icon(imageVector= Icons.Filled.ShoppingCart, contentDescription = "", tint = Color.White)
                    Spacer(modifier = Modifier.padding(8.dp))
                    Text("Buy Now", color = Color.White, fontSize = 20.sp)
                }
            }


            Spacer(modifier = Modifier.padding(8.dp))
        }
    }
}

@Composable
fun BuyNFTsConfirmationAlertDialog(openAlertDialogStatus: MutableState<Boolean>){
    if(openAlertDialogStatus.value) {
        AlertDialog(
            onDismissRequest = {},
            confirmButton = {
                Button(onClick = { /*TODO*/ }) {
                    Text("Confirm", color = Color.White, fontSize = 14.sp)
                }
            },
            dismissButton = {
                Button(onClick = { openAlertDialogStatus.value = false }) {
                    Text("Cancel", color = Color.White, fontSize = 14.sp)
                }
            },
            title = { Text("Confirmation") },
            text = { Text("Are you sure buy this NFTs") },
            containerColor = MaterialTheme.colorScheme.secondary,
            titleContentColor = Color.White,
            textContentColor = MaterialTheme.colorScheme.onSecondary

        )
    }
}




