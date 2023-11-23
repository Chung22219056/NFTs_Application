package com.example.nfts_application

import android.util.Log
import com.example.nfts_application.model.NFTs
import com.example.nfts_application.model.User
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.http.ContentType.Application.Json
import io.ktor.serialization.kotlinx.json.*
import io.metamask.androidsdk.TAG
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

@Serializable
data class LoginResponse(
    val user: User,
    val token: String
) {
    companion object {
        var loggedUser: LoginResponse? = null;
    }
}

const val API_URL = "https://d126-45-144-227-52.ngrok.io"
object KtorClient {
    val httpClient = HttpClient{
        install(ContentNegotiation){
            json(Json { ignoreUnknownKeys = true })
        }
        install(Logging)
        defaultRequest{
            contentType(ContentType.Application.Json)
            accept(ContentType.Application.Json)
            header("Authorization", "Bearer " + LoginResponse.loggedUser?.token)
        }
        expectSuccess = true
    }

}


suspend fun getBalance(): Double {
    @Serializable
    data class GetBalanceResponse(
        val balance: Double
    )
    try {
        val response = KtorClient.httpClient.post("$API_URL/api/eth/getBalance"){
            contentType(ContentType.Application.Json)
            accept(ContentType.Application.Json)
            header("Authorization", "Bearer " + LoginResponse.loggedUser?.token)
        }
        val respBody: GetBalanceResponse = response.body<GetBalanceResponse>()
        return respBody.balance
    }catch (e: Exception){
        Log.d(TAG, "Balance Error $e")
    }
    return 0.0
}

suspend fun getNFTs(amount: Int) :List<NFTs>{
    try{
        var url = "$API_URL/api/nft/getNFTs"
        if (amount > 1){
            url += "?amount=$amount"
        }
        val response = KtorClient.httpClient.get(url){
            contentType(ContentType.Application.Json)
            accept(ContentType.Application.Json)
        }
        return response.body()
    }catch (e: Exception){
        Log.d(TAG, "Get NFTs Error $e")
    }
    return listOf()
}


suspend fun getNFTDetails(id: String) : NFTs {
    try{
        val response = KtorClient.httpClient.get("$API_URL/api/nft/get_nft_details?id=$id"){
            contentType(ContentType.Application.Json)
            accept(ContentType.Application.Json)
        }
        return response.body()
    }catch (e: Exception){
        Log.d(TAG, "Get NFTs Error $e")
        throw Exception("NFTs Not Found", e)
    }

}