package com.example.toitter.repository

import com.example.toitter.repository.entity.TweatEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Component
import java.util.*

@Component
interface TweatRepository :  JpaRepository<TweatEntity, UUID> , TweatCustomRepository {

    fun findByUuid(uuid: UUID): TweatEntity?
    fun findAllByIsDeleteFalseOrderByCreatedAtDesc(): List<TweatEntity>

}