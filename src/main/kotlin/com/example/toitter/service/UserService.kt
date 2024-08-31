package com.example.toitter.service

import com.example.toitter.repository.UserRepository
import com.example.toitter.repository.entity.UserEntity
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service
import java.util.UUID


data class UserDto(val uuid: UUID? , val name: String, val email: String , val password : String){

}
@Service
class UserService(
    private val userRepository: UserRepository
){

    @Transactional
    fun createUser(userDto: UserDto): UserDto{
        val userEntity = UserEntity(name = userDto.name, email = userDto.email, password = userDto.password)
        var createUserEntity = userRepository.save(userEntity)
        return UserDto(createUserEntity.uuid,createUserEntity.name, createUserEntity.email, createUserEntity.password)
    }

    fun allList(): List<UserDto>{
        val list = userRepository.findAll();
        return list.map { entity ->
            UserDto(
                uuid = entity.uuid,
                name = entity.name,
                email = entity.email,
                password = entity.password
            )
        }
    }
    fun findByEmail(email: String): UserDto?{
        val userEntity = userRepository.findByEmail(email)
        if(userEntity != null){
            return UserDto(null,userEntity.name, userEntity.email, userEntity.password)
        }
        return null
    }


}