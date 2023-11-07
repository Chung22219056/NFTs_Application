package com.example.nfts_application.view

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@Composable
fun LoggedProfileView(){
    LazyColumn(

    ){
        item{
            Column (
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                AsyncImage(
                    model = "https://i.seadn.io/gae/qxvynIqLvCh_P3M3njdIyrCe52VoNV2hTsUlitS4VZ8jHd7PMMOHDH7cIpGaiBijORWEknoal9Bhmun2HZfNcEI35u3o93D1rROgd_I?auto=format&dpr=1&w=1000",
                    contentDescription = "",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp),
                    contentScale = ContentScale.Crop
                )
                Column (
                    modifier = Modifier
                        .fillMaxWidth()
                        .offset(y = -50.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                    AsyncImage(
                        model = "https://vuljespaarpot.nl/wp-content/uploads/2021/12/nfts-non-fungible-tokens.jpeg",
                        contentDescription = "avatar",
                        modifier = Modifier
                            .size(118.dp)
                            .clip(CircleShape)                       // clip to the circle shape
                            .border(2.dp, Color.White, CircleShape)
                    )
                }
            }
        }
    }
}