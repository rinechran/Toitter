package com.example.toitter.dto

import java.util.*


data class CreateCommentRequest(
    val tweatUuid : UUID,
    val userUuid : UUID,
    val comment : String,
    val parentCommentUuid : UUID? = null
)