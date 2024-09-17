package com.example.toitter.repository.entity

import jakarta.persistence.*
import java.time.LocalDateTime
import java.util.UUID


@Entity(name = "users")
class UsersEntity(
    @Column
    var name: String,

    @Column
    var email: String,

    @Column
    var password: String

): BaseEntity()