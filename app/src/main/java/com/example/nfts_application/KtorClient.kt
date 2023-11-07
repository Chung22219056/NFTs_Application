package com.example.nfts_application

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.http.ContentType.Application.Json
import io.ktor.serialization.kotlinx.json.*
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
@Serializable
data class User(
    val id: Int,
    val address: String,
    val name: String,
    val biography: String?,
    val email: String,
    val icon: String,
    val backdrop: String
)
object KtorClient {
    val httpClient = HttpClient{
        install(ContentNegotiation){
            json(Json { ignoreUnknownKeys = true })
        }
        install(Logging)
        defaultRequest{
            contentType(ContentType.Application.Json)
            accept(ContentType.Application.Json)
            header("Authorization", LoginResponse.loggedUser?.token)
        }
        expectSuccess = true
    }

}