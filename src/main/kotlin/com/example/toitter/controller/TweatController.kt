package com.example.toitter.controller

import CreateTweatRequest
import com.example.toitter.config.ResourceException
import com.example.toitter.dto.DeleteTweatRequest
import com.example.toitter.dto.DeleteTweatResponse
import com.example.toitter.dto.TweatDto
import com.example.toitter.dto.UpdateTweatRequest
import com.example.toitter.service.TweatService
import com.example.toitter.service.UserService
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/tweets")
class TweatController(
    private val tweatService: TweatService,
    private val userService: UserService
) {

    @GetMapping("/")
    fun tweatList(@RequestParam(required = false) name: String?, @RequestParam(required = false) msg: String?): List<TweatDto> {
        return tweatService.getList(name, msg)
    }

    @PostMapping("/")
    fun saveNewTweat(@RequestBody request: CreateTweatRequest): TweatDto {
        val userDto = userService.getFindByUUid(request.uuid) ?: throw ResourceException(404, "User not found");
        val newTweat = tweatService.create(userDto, msg = request.msg)
        return newTweat
    }
    @PutMapping("/")
    fun updateTweet(@RequestBody request: UpdateTweatRequest): TweatDto {
        val tweatEntity = tweatService.getFindByUUid(request.uuid) ?: throw ResourceException(404, "Tweet not found");
        val newTweat = tweatService.updateMsg(tweatEntity.tweatUUID, request.msg)
        return newTweat!!
    }
    @DeleteMapping("/")
    fun deleteTweet(@RequestParam request : DeleteTweatRequest): DeleteTweatResponse {
        val tweatEntity = tweatService.getFindByUUid(request.uuid) ?: throw ResourceException(404, "Tweat not found")
        tweatService.deleteTweat(tweatEntity)
        return DeleteTweatResponse("deleted tweat")
    }

}