package com.example.nfts_application.view

import android.app.Activity
import android.content.ContentResolver
import android.content.Intent
import android.net.Uri
import android.provider.MediaStore
import android.provider.OpenableColumns
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.nfts_application.API_URL
import com.example.nfts_application.KtorClient
import io.ktor.client.request.forms.formData
import io.ktor.client.request.forms.submitFormWithBinaryData
import io.ktor.http.Headers
import io.ktor.http.HttpHeaders
import io.metamask.androidsdk.TAG
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateNFTsScreen() {
    var activity = LocalContext.current as Activity
    var nftsName by remember { mutableStateOf("") }
    var nftDescription by remember { mutableStateOf("") }
    var nftPrice by remember { mutableStateOf(0.0) }
    var pickedMediaUri by remember { mutableStateOf<Uri?>(null) }
    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        println("selected file URI ${it.data?.data}")
        pickedMediaUri = it.data?.data
    }

    var textfieldColor = TextFieldDefaults.textFieldColors(
        textColor = Color.Gray,
        containerColor = MaterialTheme.colorScheme.secondary,
        placeholderColor = Color.Gray,
        unfocusedIndicatorColor = Color.Gray,
        unfocusedLabelColor = Color.Gray,
        focusedIndicatorColor = MaterialTheme.colorScheme.onPrimary,
        focusedLabelColor = MaterialTheme.colorScheme.onPrimary,
        focusedLeadingIconColor = MaterialTheme.colorScheme.onPrimary,
    )

    LazyColumn (

    ){
        item{
            Text("Create Your NFTs", fontSize = 24.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.padding(12.dp))
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = nftsName,
                onValueChange = { nftsName = it },
                label = { Text("NFT Name") }, // Corrected label
                maxLines = 1,
                colors = textfieldColor
            )

            Spacer(modifier = Modifier.padding(12.dp))
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .defaultMinSize(minHeight = 280.dp),
                value = nftDescription,
                onValueChange = { nftDescription = it },
                label = { Text("Descripiton") }, // Corrected label
                maxLines = 1,
                colors = textfieldColor
            )
            Spacer(modifier = Modifier.padding(12.dp))
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = nftPrice.toString(), // Convert Double to String
                onValueChange = {
                    // Handle non-numeric input gracefully
                    nftPrice = it.toDoubleOrNull() ?: 0.0
                },
                label = { Text("NFT Price") }, // Corrected label
                maxLines = 1,
                colors = textfieldColor,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )


            Spacer(modifier = Modifier.padding(8.dp))

            Button(
                onClick = {
                    val intent = Intent(Intent.ACTION_OPEN_DOCUMENT, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                        .apply {
                            addCategory(Intent.CATEGORY_OPENABLE)
                        }
                    launcher.launch(intent)
                },
            ) {
                Text("Select File", color = Color.White)
            }

            Button(
                onClick = {
                    GlobalScope.launch {
                        SendMintNFTsRequest(nftsName, nftDescription, nftPrice, pickedMediaUri, activity)
                    }
                },
            ) {
                Text("Mint NFTs", color = Color.White)
            }
        }
    }
}


suspend fun SendMintNFTsRequest(name: String, description: String, price: Double, media: Uri?, activity: Activity){

    var inputStream = media?.let { activity.getContentResolver().openInputStream(it) }
    var byteArray = inputStream?.readBytes()

    var filename = queryName(activity.contentResolver, media!!)
    Log.d(TAG, "Filename $filename")
    val formData = formData {
        append("name", name)
        append("description", description)
        append("price", price)
        append("royalties", price)
        append("media", byteArray!!, Headers.build {
            append(HttpHeaders.ContentDisposition, "filename=$filename")
        })
    }
    try{
        KtorClient.httpClient.submitFormWithBinaryData(
            url = "$API_URL/api/nft/mintNFTs",
            formData = formData
        )
    }catch (e: Exception){
        Log.e(TAG, e.toString())
    }

}

fun queryName(resolver: ContentResolver, uri: Uri): String? {
    val returnCursor = resolver.query(uri, null, null, null, null)!!
    val nameIndex = returnCursor.getColumnIndex(OpenableColumns.DISPLAY_NAME)
    returnCursor.moveToFirst()
    val name = returnCursor.getString(nameIndex)
    returnCursor.close()
    return name
}

