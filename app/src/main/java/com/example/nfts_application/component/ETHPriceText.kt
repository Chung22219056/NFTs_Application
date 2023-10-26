package com.example.nfts_application.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.nfts_application.R

@Composable
fun ETHPriceText(price: String){
    Row(
        verticalAlignment= Alignment.CenterVertically
    ){
        Image(
            modifier = Modifier
                .size(32.dp),
            painter = painterResource(id = R.drawable.eth),
            contentDescription = "",
            contentScale = ContentScale.Crop
        )
        Text(price, fontSize = 18.sp)
    }

}