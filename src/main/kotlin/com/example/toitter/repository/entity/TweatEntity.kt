package com.example.toitter.repository.entity

import jakarta.persistence.*

@Entity(name = "tweat")
data class TweatEntity(

    @ManyToOne
    @JoinColumn(name = "user_uuid",referencedColumnName = "uuid")
    var usersEntity: UsersEntity,

    @Column(name = "msg")
    var msg: String,

) : BaseEntity()