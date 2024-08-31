package com.example.toitter.controller.user

import com.example.toitter.config.ResourceException
import com.example.toitter.service.UserDto
import com.example.toitter.service.UserService
import org.springframework.web.bind.annotation.*

data class CreateUserRequest(
    val name: String,
    val email: String,
    val password: String,
)


@RestController
@RequestMapping("/users")
class UserController(
    private val userService: UserService
){

    @GetMapping("/")
    fun getUsers() : List<UserDto>{
        return userService.allList()
    }

    @PostMapping("/")
    fun createUser(@RequestBody user : CreateUserRequest) : UserDto{
        if(userService.findByEmail(user.email) != null){
            throw ResourceException(302,"Email already exists")
        }
        val createUser = userService.createUser(
            UserDto(null,user.name, user.email, user.password)
        )
        return createUser
    }

}