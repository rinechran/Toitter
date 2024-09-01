package com.example.toitter.dto

import java.util.UUID

data class UpdateCommentRequest(
    val uuid : UUID,
    val comment : String
)