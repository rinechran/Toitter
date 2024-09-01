package com.example.toitter.repository.entity

import jakarta.persistence.*
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.Instant
import java.time.LocalDateTime
import java.util.UUID

@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
abstract class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    var uuid: UUID? = null


    @Column(name = "create_data_at", updatable = false)
    @CreatedDate
    lateinit  var createdAt: Instant
        protected set

    @LastModifiedDate
    @Column(name = "update_data_at")
    lateinit var updatedAt: Instant
        protected set

    @Column(name = "is_delete")
    var isDelete : Boolean = false

}