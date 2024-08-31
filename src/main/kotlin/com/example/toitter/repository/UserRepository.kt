package com.example.toitter.repository

import com.example.toitter.repository.entity.UserEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Component

@Component
interface UserRepository : JpaRepository<UserEntity, Long> {
}