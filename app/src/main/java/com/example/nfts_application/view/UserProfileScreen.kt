package com.example.nfts_application.view

import android.util.Log
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.nfts_application.Greeting
import com.example.nfts_application.ui.theme.NFTs_ApplicationTheme
import io.metamask.androidsdk.Dapp
import java.util.UUID
import io.metamask.androidsdk.Ethereum
import io.metamask.androidsdk.RequestError
import io.metamask.androidsdk.TAG


@Composable
fun UserProfileScreen(){
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

                Text("ZO werken", fontSize = 24.sp, color = Color.White, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.padding(4.dp))
                Text("Lorem, ipsum dolor sit amet consectetur adipisicing elit. Voluptate, quod repellat! Quis quos dolorum tenetur fuga? Aspernatur rerum quae amet.", textAlign = TextAlign.Center)
            }

        }
    }
}




@Preview(showBackground = true)
@Composable
fun UserProfileScreenPreview() {
    NFTs_ApplicationTheme {
        UserProfileScreen()
    }
}

