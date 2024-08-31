package com.example.toitter.repository.entity

import jakarta.persistence.*
import java.time.LocalDateTime
import java.util.*

@Entity
data class TweatEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val uuid: UUID? = null,

    @ManyToOne
    @JoinColumn(name = "user_uuid")
    var user: UserEntity,

    var msg: String,

    var createDataAt : LocalDateTime = LocalDateTime.now(),
)