package com.example.toitter.controller.tweat

import com.example.toitter.repository.entity.TweatEntity
import com.example.toitter.service.TweatService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/tweets")
class TweatController(private val tweatService: TweatService) {

    @GetMapping("/")
    fun tweatList(@RequestParam(required = false) name: String?, @RequestParam(required = false) msg: String?): List<TweatEntity> {
        return tweatService.getList(name, msg)
    }
    data class CreateTweatRequest(
        val name: String,
        val msg: String
    )
    @PostMapping("/")
    fun saveNewTweat(@RequestBody request: CreateTweatRequest): ResponseEntity<TweatEntity> {
        val temp = TweatEntity(
            name = request.name,
            msg = request.msg
        )
        val newTweat = tweatService.create(temp)
        return ResponseEntity.status(HttpStatus.CREATED).body(newTweat)
    }


}