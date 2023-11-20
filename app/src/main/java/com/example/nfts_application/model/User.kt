package com.example.nfts_application.model

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val id: Int,
    val address: String,
    val name: String,
    val biography: String?,
    val email: String,
    val icon: String?,
    val backdrop: String?
)