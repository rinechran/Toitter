package com.example.toitter.service

import com.example.toitter.dto.UserDto
import com.example.toitter.repository.UsersRepository
import com.example.toitter.repository.entity.UsersEntity
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service
import java.util.UUID



@Service
class UserService(
    private val userRepository: UsersRepository
){

    @Transactional
    fun createUser(  name: String,  email: String,  password : String): UserDto{
        val userEntity = UsersEntity(name = name, email = email, password = password)
        var createUserEntity = userRepository.save(userEntity)
        return UserDto(
            createUserEntity.uuid!!,
            createUserEntity.name,
            createUserEntity.email,
            createUserEntity.password,
            createUserEntity.isDelete)
    }

    fun allList(): List<UserDto>{
        val list = userRepository.findAllByIsDeleteFalse();
        return list.map { entity ->
            UserDto(
                uuid = entity.uuid!!,
                name = entity.name,
                email = entity.email,
                password = entity.password,
                isDelete = entity.isDelete
            )
        }
    }
    fun findByEmail(email: String): UserDto?{
        val userEntity = userRepository.findByEmail(email) ?: return null
        return UserDto(userEntity.uuid!!,
            userEntity.name,
            userEntity.email,
            userEntity.password,
            isDelete = userEntity.isDelete
        )
    }

    fun findUserByEmailAndPasswordAndDeleteIsNot(email: String , password: String ): UserDto?{
        val userEntity = userRepository.findByEmailAndPasswordAndIsDeleteFalse(email,password) ?: return null
        return UserDto(userEntity.uuid!!,
            userEntity.name,
            userEntity.email,
            userEntity.password,
            isDelete = userEntity.isDelete
        )
    }

    @Transactional
    fun deleteByUser(userDto: UserDto): UserDto?{
        var userEntity = userRepository.findByUuid(userDto.uuid) ?: return null
        userEntity.isDelete = true
        userRepository.save(userEntity)
        return UserDto(userEntity.uuid!!,userEntity.name, userEntity.email, userEntity.password , isDelete = userEntity.isDelete)
    }

    fun getFindByUUid(uuid: UUID): UserDto?{
        var userEntity = userRepository.findByUuid(uuid) ?: return null
        return UserDto(
            userEntity.uuid!!,
            userEntity.name,
            userEntity.email,
            userEntity.password ,
            isDelete = userEntity.isDelete
        )

    }


}