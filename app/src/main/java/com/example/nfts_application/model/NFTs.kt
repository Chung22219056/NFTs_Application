package com.example.nfts_application.model

import com.example.nfts_application.API_URL
import kotlinx.serialization.Serializable

@Serializable
data class NFTs (
    val id: Int,
    val name: String,
    val url: String,
    val tokenID: String,
    val price: Double,
    val description: String

){
    fun getImageURL(): String {
        return "$API_URL/api/nft/imageURL?url=$url"
    }
}