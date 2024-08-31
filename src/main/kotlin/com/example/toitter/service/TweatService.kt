package com.example.toitter.service

import com.example.toitter.config.ResourceException
import com.example.toitter.repository.TweatRepository
import com.example.toitter.repository.UserRepository
import com.example.toitter.repository.entity.TweatEntity
import com.example.toitter.repository.entity.UserEntity
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.util.UUID
import javax.naming.Name


data class TweatDto(
    val userUUID: UUID,
    val msg: String,
    val name : String?,
    val tweatUUID: UUID?,
    val createDataAt : LocalDateTime?
)

@Service
class TweatService(
    private val tweatRepository: TweatRepository,
    private val userRepository: UserRepository

) {

    fun getList(name: String?, msg: String?): List<TweatDto> {
        val tweatEntityList = if (name != null || msg != null) {
            tweatRepository.findByUser_NameContainingAndMsgContaining(name ?: "", msg ?: "")
        } else {
            tweatRepository.findAll()
        }

        return tweatEntityList.map { tweatEntity ->
            TweatDto(
                userUUID = tweatEntity.user.uuid!!,
                msg = tweatEntity.msg,
                name = tweatEntity.user.name,
                tweatUUID = tweatEntity.uuid,
                createDataAt = tweatEntity.createDataAt
            )
        }
    }

    @Transactional
    fun create(userUUID: UUID , tweat: TweatDto): TweatDto {
        val user : UserEntity = userRepository.findByUuid(userUUID) ?: throw ResourceException(404, "User not found");
        val tweatEntity  = TweatEntity(null,user,tweat.msg)
        tweatRepository.save(tweatEntity)
        return TweatDto( userUUID,tweat.msg,user.name,tweatEntity.uuid,tweatEntity.createDataAt)
    }
    @Transactional
    fun updateMsg(tweatUUID: UUID, newMsg: String): TweatDto {
        val tweatEntity = tweatRepository.findByUuid(tweatUUID) ?: throw ResourceException(404, "Tweat not found")
        tweatEntity.msg = newMsg
        tweatRepository.save(tweatEntity)
        return TweatDto(
            userUUID = tweatEntity.user.uuid!!,
            tweatUUID = tweatEntity.uuid!!,
            msg = tweatEntity.msg,
            name = tweatEntity.user.name,
            createDataAt = tweatEntity.createDataAt
        )
    }

}