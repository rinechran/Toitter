package com.example.toitter.dto

import java.util.*

data class UserDto(val uuid: UUID, val name: String, val email: String, val password : String, val isDelete: Boolean ){

}