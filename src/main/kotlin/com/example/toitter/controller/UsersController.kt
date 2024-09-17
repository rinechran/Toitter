package com.example.toitter.controller

import com.example.toitter.appException.ResourceException
import com.example.toitter.dto.CreateUsersRequest
import com.example.toitter.dto.DeleteUsersRequest
import com.example.toitter.dto.DeleteUsersResponse
import com.example.toitter.dto.UserDto
import com.example.toitter.service.UserService
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/users")
class UsersController(
    private val userService: UserService
){

    @GetMapping("/")
    fun getUsers() : List<UserDto>{
        return userService.allList()
    }

    @PostMapping("/")
    fun createUser(@RequestBody user : CreateUsersRequest) : UserDto{
        if(userService.findByEmail(user.email) != null){
            throw ResourceException(302,"Email already exists")
        }
        val createUser = userService.createUser(
            user.name, user.email, user.password
        )
        return createUser
    }
    @DeleteMapping("/")
    fun deleteUser(@RequestBody user : DeleteUsersRequest)  : DeleteUsersResponse {
        val user = userService.getFindByUUid(user.userUuid) ?: throw ResourceException(404, "User not found")
        userService.deleteByUser(user)
        return DeleteUsersResponse(reason = "User Delete");
    }

}