package com.example.toitter.repository

import com.example.toitter.repository.entity.TweatEntity
import com.example.toitter.repository.entity.UserEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Component
import java.util.*

@Component
interface TweatRepository :  JpaRepository<TweatEntity, Long> {
    fun findByUser_NameContainingAndMsgContaining(name: String, msg: String): List<TweatEntity>
    fun findByUuid(uuid: UUID): TweatEntity?

}