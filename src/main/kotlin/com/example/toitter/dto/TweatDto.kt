package com.example.toitter.dto

import java.time.Instant
import java.time.LocalDateTime
import java.util.*

data class TweatDto(
    val userUUID: UUID,
    val msg: String,
    val name : String,
    val tweatUUID: UUID,
    val isDeleted : Boolean,
    val createDataAt : Instant,
    val updateDataAt : Instant,
)