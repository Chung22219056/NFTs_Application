package com.example.nfts_application.view

import android.content.Context
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Wallet
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.nfts_application.R
import io.metamask.androidsdk.Dapp
import io.metamask.androidsdk.Ethereum
import io.metamask.androidsdk.RequestError
import io.metamask.androidsdk.TAG

@Composable
fun ProfileScreen(navController: NavHostController){
    NavHost(navController = navController, startDestination = "profileHomeScreen"){
        composable("profileHomeScreen"){
            ProfileHomeScreen()
        }
    }


}

@Composable
fun ProfileHomeScreen(){
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Icon(Icons.Filled.Wallet, contentDescription="", modifier = Modifier.size(80.dp))
        Text("Connect Your Wallet", fontSize = 20.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.padding(8.dp))
        Text("Your crypto wallet securely stores your digital goods and cryptocurrencies. Connect to one of our wallet provider or create a new one", textAlign= TextAlign.Center, color = MaterialTheme.colorScheme.onSecondary)
        Spacer(modifier = Modifier.padding(14.dp))
        WalletCard()
    }
}


@Composable
fun WalletCard(){
    val context: Context = LocalContext.current

    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.secondary,
        ),
        modifier = Modifier.fillMaxWidth().clickable { ConnectMetamask(context) }
    ){
        Row (
            modifier = Modifier.padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ){
            Image(
                painter = painterResource(id = R.drawable.metamask_fox_svg),
                contentDescription = "",
                modifier = Modifier.size(54.dp)
            )
            Spacer(modifier = Modifier.padding(12.dp))
            Text("MetaMask", fontWeight = FontWeight.Bold, fontSize = 20.sp)

        }

    }
}



fun ConnectMetamask(context: Context) {

    val ethereum = Ethereum(context)

    val dapp = Dapp("Droid Dapp", "https://droiddapp.com")

    // This is the same as calling eth_requestAccounts
    ethereum.connect(dapp) { result ->
        if (result is RequestError) {
            Log.e(TAG, "Ethereum connection error: ${result.message}")
        } else {
            Log.d(TAG, "Ethereum connection result: $result")
        }
    }
}