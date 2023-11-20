package com.example.nfts_application.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CurrencyExchange
import androidx.compose.material.icons.filled.MonetizationOn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.nfts_application.R
import com.example.nfts_application.ScaffoldScreen
import com.example.nfts_application.ui.theme.NFTs_ApplicationTheme


data class BalanceOption(
    val title: String,
    val icon: ImageVector,
    val navigation: String
){
    companion object{
        val data = listOf(
            BalanceOption("TopUp", Icons.Filled.MonetizationOn, ""),
            BalanceOption("Transaction", Icons.Filled.CurrencyExchange, ""),
        )
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun BalanceScreen(){
    LazyColumn(

    ){
        item{
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.onPrimary,
                ),
            ) {
                Column(
                    modifier = Modifier.padding(8.dp)
                ) {
                    Row(
                        verticalAlignment= Alignment.CenterVertically
                    ){
                        Image(painter = painterResource(id= R.drawable.eth), contentDescription = "eth", modifier = Modifier.size(42.dp))
                        Text(text = "Your Ethereum Balance", fontSize = 18.sp)
                    }
                    Spacer(modifier = Modifier.padding(8.dp))
                    Text("10.000 ETH", fontSize = 38.sp, fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.padding(8.dp))
                }
            }
        }
        item{
            Spacer(modifier = Modifier.padding(12.dp))
            Text("Options", fontWeight = FontWeight.Bold, fontSize = 18.sp, color = Color.White)
            Spacer(modifier = Modifier.padding(4.dp))
            FlowRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                maxItemsInEachRow = 4
            ){
                for (item in BalanceOption.data){
                    OptionButton(item)
                }
            }
        }
    }
}
@Composable
fun OptionButton(option: BalanceOption){
    Column {
        ElevatedCard(
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.secondary,
            ),
            modifier = Modifier.clickable {
            }
        ) {
            Column (
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.width(128.dp).padding(8.dp)
            ){
                Icon(option.icon, contentDescription = option.title, modifier = Modifier.size(38.dp))
                Spacer(modifier = Modifier.padding(4.dp))
                Text(option.title, fontWeight=FontWeight.Bold)
            }
        }
        Spacer(modifier = Modifier.padding(8.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun BalanceScreenPreview() {
    NFTs_ApplicationTheme {
        BalanceScreen()
    }
}