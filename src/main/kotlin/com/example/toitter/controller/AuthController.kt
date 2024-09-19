package com.example.toitter.controller

import com.example.toitter.appException.ResourceException
import com.example.toitter.dto.LoginAuthRequest
import com.example.toitter.service.AuthService
import org.springframework.boot.autoconfigure.security.SecurityProperties
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/auth")
class AuthController(
    val authService: AuthService,
){

    @PostMapping("")
    fun login(@RequestBody user: LoginAuthRequest) : String{
        val findUser = authService.findUser(user.email, user.password);
        if (findUser != null){
            throw ResourceException(404, "Not Find Login")
        }
        return ""
    }
}