package com.example.toitter.controller.tweat

import com.example.toitter.service.TweatDto
import com.example.toitter.service.TweatService
import org.springframework.web.bind.annotation.*
import java.util.UUID

data class CreateOrUpdateTweatRequest(
    val uuid : UUID,
    val msg: String
)


@RestController
@RequestMapping("/tweets")
class TweatController(
    private val tweatService: TweatService
) {

    @GetMapping("/")
    fun tweatList(@RequestParam(required = false) name: String?, @RequestParam(required = false) msg: String?): List<TweatDto> {
        return tweatService.getList(name, msg)
    }

    @PostMapping("/")
    fun saveNewTweat(@RequestBody request: CreateOrUpdateTweatRequest):TweatDto {
        val newTweat = tweatService.create(
            request.uuid,
            TweatDto(userUUID = request.uuid, msg = request.msg,null,null,null)
        )
        return newTweat
    }
    @PutMapping("/")
    fun updateTweetat(@RequestBody request:CreateOrUpdateTweatRequest):TweatDto {
        val newTweat = tweatService.updateMsg(request.uuid, request.msg)

        return newTweat
    }

}