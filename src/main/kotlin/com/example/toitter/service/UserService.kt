package com.example.toitter.service

import com.example.toitter.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userRepository: UserRepository
){


}