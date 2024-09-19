package com.example.toitter.service

import com.example.toitter.dto.UserDto
import org.springframework.stereotype.Service

@Service
class AuthService(
    private val userService: UserService
){
    fun findUser(email : String, password : String) : UserDto? {
        return userService.findUserByEmailAndPasswordAndDeleteIsNot(email , password);
    }

}