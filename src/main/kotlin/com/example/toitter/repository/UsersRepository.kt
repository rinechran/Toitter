package com.example.toitter.repository

import com.example.toitter.repository.entity.UsersEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Component
import java.util.*

@Component
interface UsersRepository : JpaRepository<UsersEntity, UUID> {
    fun findByEmail(email: String): UsersEntity?
    fun findByUuid(uuid: UUID): UsersEntity?
    fun findAllByIsDeleteFalse(): List<UsersEntity>

}