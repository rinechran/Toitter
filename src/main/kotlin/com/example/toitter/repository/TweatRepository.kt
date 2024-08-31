package com.example.toitter.repository

import com.example.toitter.repository.entity.TweatEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Component

@Component
interface TweatRepository :  JpaRepository<TweatEntity, Long> {
    fun findByNameContainingAndMsgContaining(name: String, msg: String): List<TweatEntity>
}