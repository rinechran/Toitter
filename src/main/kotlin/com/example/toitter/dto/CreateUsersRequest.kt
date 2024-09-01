package com.example.toitter.dto

data class CreateUsersRequest(
    val name: String,
    val email: String,
    val password: String,
)