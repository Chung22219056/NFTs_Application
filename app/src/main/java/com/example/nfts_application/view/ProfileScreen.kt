package com.example.nfts_application.view

import android.content.Context
import android.graphics.drawable.PaintDrawable
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.nfts_application.R
import com.example.nfts_application.viewModel.EthereumViewModel
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment


data class User(val token: String){

}
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
    val openAlertDialog = remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Icon(Icons.Filled.Wallet, contentDescription="", modifier = Modifier.size(80.dp))
        Text("Connect Your Wallet", fontSize = 20.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.padding(8.dp))
        Text("Your crypto wallet securely stores your digital goods and cryptocurrencies. Connect to one of our wallet provider or create a new one", textAlign= TextAlign.Center, color = MaterialTheme.colorScheme.onSecondary)
        Spacer(modifier = Modifier.padding(14.dp))

        WalletCard(image = R.drawable.ethereum_eth_icon, title = "Ethereum Address", openAlertDialog)
        WalletCard(image = R.drawable.metamask_fox_svg, title ="MetaMask", openAlertDialog)
    }

    if(openAlertDialog.value){
        ConnectEthereumAddressAlert(openAlertDialog)
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
                if(title == "Ethereum Address")
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConnectEthereumAddressAlert(openAlertDialog: MutableState<Boolean>){
    var addressInputText = ""
    var passwordInputText = ""

    Dialog(onDismissRequest = {}) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(420.dp)
                .padding(16.dp),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.secondary,
                contentColor = MaterialTheme.colorScheme.onSecondary
            )
        ){

            Column (
                modifier = Modifier
                    .padding(12.dp)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ){

                Column ( horizontalAlignment = Alignment.End, modifier = Modifier.fillMaxWidth()){
                    IconButton(onClick = {  openAlertDialog.value = false }) {
                        Icon(Icons.Filled.Close, contentDescription = "close")
                    }
                }
                Image(painter = painterResource(R.drawable.ethereum_eth_icon), contentDescription = "ethereum_eth_icon", modifier = Modifier.size(48.dp))
                Spacer(modifier = Modifier.padding(4.dp))
                Text("Ethereum Mainnet")
                Spacer(modifier = Modifier.padding(12.dp))

                OutlinedTextField(
                    value = addressInputText,
                    onValueChange = { addressInputText = it },
                    label = { Text("Address") },
                    maxLines = 1
                )
                Spacer(modifier = Modifier.padding(8.dp))
                OutlinedTextField(
                    value = passwordInputText,
                    onValueChange = { passwordInputText = it },
                    label = { Text("Password") },
                    maxLines = 1
                )
                Spacer(modifier = Modifier.padding(8.dp))
                Button(onClick = { /*TODO*/ }) {
                    Text("Connect", color = Color.White)
                }
            }
        }
    }
}
