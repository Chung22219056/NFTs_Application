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
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Wallet
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.nfts_application.API_URL
import com.example.nfts_application.KtorClient
import com.example.nfts_application.KtorClient.httpClient
import com.example.nfts_application.LoginResponse
import com.example.nfts_application.R
import com.example.nfts_application.component.ProfileScreen.ConnectEthereumAddressAlert
import com.example.nfts_application.viewModel.EthereumViewModel
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.util.InternalAPI
import io.metamask.androidsdk.TAG
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable


@Composable
fun ProfileScreen(navController: NavHostController){
    val refresh = remember {
        mutableStateOf(false)
    }
    NavHost(navController = navController, startDestination = "profileHomeScreen"){
        composable("profileHomeScreen"){
            if(LoginResponse.loggedUser != null){
                LoggedProfileView()
            }else{
                ProfileHomeScreen(navController, refresh)
            }

            if(refresh.value){
                navController.popBackStack()
                refresh.value = false
            }
        }
    }
}

@Composable
fun ProfileHomeScreen(navController: NavHostController, refresh: MutableState<Boolean>){
    val openAlertDialog = remember { mutableStateOf(false) }
    val loggedIn = remember {
        mutableStateOf(false)
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Icon(Icons.Filled.Wallet, contentDescription="", modifier = Modifier.size(80.dp))
        Text("Connect Your Wallet", fontSize = 20.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.padding(8.dp))
        Text("Your crypto wallet securely stores your digital goods and cryptocurrencies. Connect to one of our wallet provider or create a new one", textAlign= TextAlign.Center, color = MaterialTheme.colorScheme.onSecondary)
        Spacer(modifier = Modifier.padding(14.dp))

        WalletCard(image = R.drawable.ethereum_eth_icon, title = "Ethereum Mainnet", openAlertDialog)
        WalletCard(image = R.drawable.metamask_fox_svg, title ="MetaMask", openAlertDialog)
    }

    if(openAlertDialog.value){
        ConnectEthereumAddressAlert(openAlertDialog, loggedIn)
    }

    if(loggedIn.value){
        navController.navigate("profileHomeScreen")
        refresh.value = true
        loggedIn.value = false
    }

}


@Composable
fun WalletCard(image: Int, title: String, openAlertDialog: MutableState<Boolean>){

    val context: Context = LocalContext.current

    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.secondary,
        ),
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                if (title == "Ethereum Mainnet")
                    openAlertDialog.value = true
            }
    ){
        Row (
            modifier = Modifier.padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ){
            Image(
                painter = painterResource(id = image),
                contentDescription = "",
                modifier = Modifier.size(54.dp)
            )
            Spacer(modifier = Modifier.padding(12.dp))
            Text(title, fontWeight = FontWeight.Bold, fontSize = 20.sp)

        }
    }

    Spacer(modifier = Modifier.padding(8.dp))
}



fun ConnectMetamask(context: Context) {
    val Eth = EthereumViewModel(context)
    Eth.connect()
}






