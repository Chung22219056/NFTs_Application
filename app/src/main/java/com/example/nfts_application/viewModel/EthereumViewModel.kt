package com.example.nfts_application.viewModel

import android.content.Context
import android.util.Log
import io.metamask.androidsdk.Dapp
import io.metamask.androidsdk.Ethereum
import io.metamask.androidsdk.EthereumMethod
import io.metamask.androidsdk.EthereumRequest
import io.metamask.androidsdk.RequestError
import io.metamask.androidsdk.TAG


class EthereumViewModel(val context: Context){
    private val ethereum: Ethereum = Ethereum(context = context)
    public var balance: String = "0";

    fun connect(){
        val dapp = Dapp("Droid Dapp", "https://ddad-223-18-29-104.ngrok.io")
        ethereum.enableDebug(true)

        // This is the same as calling eth_requestAccounts
        this.ethereum.connect(dapp) { result ->
            if (result is RequestError) {
                Log.e(TAG, "Ethereum connection error: ${result.message}")
            } else {
                Log.d(TAG, "Ethereum connection result (Success): $result")
                Log.d(TAG,"SELECTED ADDR " + ethereum.selectedAddress)
            }
        }
    }

    fun transaction(){
        val from = "0xb7e0c1c3702b99a3f72337443870ca6b32b8987f"
        val to = "0x0103e77d6C133a41A76b42C031Fb43eEDA121595"
        val amount = "0x01"
        val params: Map<String, Any> = mapOf(
            "from" to from,
            "to" to to,
            "amount" to amount
        )

        val transactionRequest = EthereumRequest(
            method = EthereumMethod.ETH_SEND_TRANSACTION.value,
            params = listOf(params)
        )

        ethereum.sendRequest(transactionRequest) { result ->
            if (result is RequestError) {
                // handle error
                Log.e(TAG, "Ethereum transaction result Error: $result")
            } else {
                Log.d(TAG, "Ethereum transaction result (Success): $result")
            }

            Log.d(TAG, "transaction RESULT: $result")
        }
    }
}