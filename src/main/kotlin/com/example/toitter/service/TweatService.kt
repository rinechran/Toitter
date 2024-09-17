package com.example.toitter.service

import com.example.toitter.dto.TweatDto
import com.example.toitter.dto.UserDto
import com.example.toitter.repository.TweatRepository
import com.example.toitter.repository.entity.TweatEntity
import com.example.toitter.repository.entity.UsersEntity
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service
import java.util.UUID



@Service
class TweatService(
    private val tweatRepository: TweatRepository
) {

    fun getList(name: String?, msg: String?): List<TweatDto> {
        val tweatEntityList = if (name != null || msg != null) {
            tweatRepository.findByUsersEntity_NameContainingAndMsgContainingAndIsDeleteFalseOrderByCreatedAtAsc(name , msg )
        } else {
            tweatRepository.findAllByIsDeleteFalseOrderByCreatedAtDesc()
        }

        return tweatEntityList.map { tweatEntity ->
            TweatDto(
                userUUID = tweatEntity.usersEntity.uuid!!,
                msg = tweatEntity.msg,
                name = tweatEntity.usersEntity.name,
                tweatUUID = tweatEntity.uuid!!,
                isDeleted = tweatEntity.isDelete,
                createDataAt = tweatEntity.createdAt,
                updateDataAt = tweatEntity.updatedAt
            )
        }
    }

    @Transactional
    fun create(userDto : UserDto , msg : String): TweatDto {
        val usersEntity = UsersEntity(
            name = userDto.name,
            email = userDto.email,
            password = userDto.email
        )
        usersEntity.uuid = userDto.uuid

        val tweatEntity  = TweatEntity(usersEntity,msg)
        tweatRepository.save(tweatEntity)
        return TweatDto(
            userUUID = tweatEntity.usersEntity.uuid!!,
            msg = tweatEntity.msg,
            name = tweatEntity.usersEntity.name,
            tweatUUID = tweatEntity.uuid!!,
            createDataAt = tweatEntity.createdAt,
            updateDataAt = tweatEntity.updatedAt,
            isDeleted = tweatEntity.isDelete,
        )


    }
    @Transactional
    fun updateMsg(tweatUUID: UUID, newMsg: String): TweatDto? {
        val tweatEntity = tweatRepository.findByUuid(tweatUUID) ?: return null
        tweatEntity.msg = newMsg
        tweatRepository.save(tweatEntity)
        return TweatDto(
            userUUID = tweatEntity.usersEntity.uuid!!,
            tweatUUID = tweatEntity.uuid!!,
            msg = tweatEntity.msg,
            name = tweatEntity.usersEntity.name,
            isDeleted = tweatEntity.isDelete,
            createDataAt = tweatEntity.createdAt,
            updateDataAt = tweatEntity.updatedAt
        )
    }
    @Transactional
    fun deleteTweat(tweatDto: TweatDto) : TweatDto? {
        val tweatEntity = tweatRepository.findByUuid(tweatDto.tweatUUID) ?: return null
        tweatEntity.isDelete = true
        tweatRepository.save(tweatEntity)
        return TweatDto(
            userUUID = tweatEntity.usersEntity.uuid!!,
            tweatUUID = tweatEntity.uuid!!,
            msg = tweatEntity.msg,
            name = tweatEntity.usersEntity.name,
            isDeleted = tweatEntity.isDelete,
            createDataAt = tweatEntity.createdAt,
            updateDataAt = tweatEntity.updatedAt
        )

    }
    fun getFindByUUid(uuid: UUID) : TweatDto? {
        val tweatEntity = tweatRepository.findByUuid(uuid) ?: return null
        return TweatDto(
            userUUID = tweatEntity.usersEntity.uuid!!,
            tweatUUID = tweatEntity.uuid!!,
            msg = tweatEntity.msg,
            name = tweatEntity.usersEntity.name,
            isDeleted = tweatEntity.isDelete,
            createDataAt = tweatEntity.createdAt,
            updateDataAt = tweatEntity.updatedAt
        )
    }
}