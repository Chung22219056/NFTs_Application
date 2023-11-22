package com.example.nfts_application.component.ProfileScreen

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.nfts_application.API_URL
import com.example.nfts_application.KtorClient
import com.example.nfts_application.LoginResponse
import com.example.nfts_application.R
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConnectEthereumAddressAlert(openAlertDialog: MutableState<Boolean>, loggedIn: MutableState<Boolean>){
    val loginFailedStatus = remember {
        mutableStateOf(false)
    }
    var emailInputText by remember { mutableStateOf("example@email.com") }
    var passwordInputText by remember { mutableStateOf("AlanPassword") }

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

                if(loginFailedStatus.value){
                    Text("Login failed", color= Color.Red, modifier = Modifier.align(Alignment.Start))
                }


                OutlinedTextField(
                    value = emailInputText,
                    onValueChange = { emailInputText = it },
                    label = { Text("Email Address") },
                    maxLines = 1,
                    colors = TextFieldDefaults.textFieldColors(
                        textColor = Color.Gray,
                        containerColor = MaterialTheme.colorScheme.secondary,
                        placeholderColor = Color.Gray,
                        unfocusedIndicatorColor = Color.Gray,
                        unfocusedLabelColor = Color.Gray,
                        focusedIndicatorColor = MaterialTheme.colorScheme.onPrimary,
                        focusedLabelColor = MaterialTheme.colorScheme.onPrimary,
                        focusedLeadingIconColor = MaterialTheme.colorScheme.onPrimary,
                    )
                )
                Spacer(modifier = Modifier.padding(8.dp))
                OutlinedTextField(
                    value = passwordInputText,
                    onValueChange = { passwordInputText = it },
                    label = { Text("Password") },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Password
                    ),
                    maxLines = 1,
                    colors = TextFieldDefaults.textFieldColors(
                        textColor = Color.Gray,
                        containerColor = MaterialTheme.colorScheme.secondary,
                        placeholderColor = Color.Gray,
                        unfocusedIndicatorColor = Color.Gray,
                        unfocusedLabelColor = Color.Gray,
                        focusedIndicatorColor = MaterialTheme.colorScheme.onPrimary,
                        focusedLabelColor = MaterialTheme.colorScheme.onPrimary,
                        focusedLeadingIconColor = MaterialTheme.colorScheme.onPrimary,
                    )
                )
                Spacer(modifier = Modifier.padding(8.dp))
                Button(onClick = {
                    GlobalScope.launch {
                        loginWithETHBlockChain(emailInputText, passwordInputText, loggedIn, openAlertDialog, loginFailedStatus)
                    }

                }) {
                    Text("Connect", color = Color.White)
                }
            }
        }
    }
}


@Serializable
data class LoginRequestBody(
    val email: String,
    val password: String
)
@OptIn(InternalAPI::class)
suspend fun loginWithETHBlockChain(email: String, password: String, loggedIn: MutableState<Boolean>, openAlertDialog: MutableState<Boolean>, loginFailedStatus: MutableState<Boolean>){
    try {
        val response = KtorClient.httpClient.post("$API_URL/api/login"){
            contentType(ContentType.Application.Json)
            setBody(LoginRequestBody(email, password))
        }
        val respBody: LoginResponse = response.body<LoginResponse>()
        if(respBody.token != ""){
            LoginResponse.loggedUser = respBody
        }
        //Log.d(TAG, LoginResponse.loggedUser?.token.toString())
        openAlertDialog.value = false
        loggedIn.value = true
        Log.d(TAG, "Success")

    }catch (e: Exception){
        Log.e(TAG, "ERROR "+ e)
        loginFailedStatus.value = true
    }
}
