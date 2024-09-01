package com.example.toitter.dto

import java.util.*

data class UpdateTweatRequest(
    val uuid : UUID,
    val msg: String
)