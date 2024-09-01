package com.example.toitter.controller

import com.example.toitter.config.ResourceException
import com.example.toitter.dto.CommentDto
import com.example.toitter.dto.CreateCommentRequest
import com.example.toitter.dto.UpdateCommentRequest
import com.example.toitter.service.CommentService
import com.example.toitter.service.TweatService
import com.example.toitter.service.UserService
import org.springframework.web.bind.annotation.*
import java.util.UUID


@RestController
@RequestMapping("/comments")
class CommentController(
    val commentService: CommentService,
    val tweatService: TweatService,
    val userService: UserService
){

    @GetMapping("/")
    fun listComment(@RequestParam(required = true) tweatUuid: String?,): List<CommentDto> {
        return commentService.getComment(UUID.fromString(tweatUuid))
    }

    @PostMapping("/")
    fun createComment(@RequestBody request : CreateCommentRequest): CommentDto {

        tweatService.getFindByUUid(request.tweatUuid)
            ?: throw ResourceException(404, "Tweat not found")

        userService.getFindByUUid(request.userUuid)
            ?: throw ResourceException(404, "User not found")

        request.parentCommentUuid?.let { parentUuid ->
            commentService.findByUUid(parentUuid)
                ?: throw ResourceException(404, "Parent comment not found")
        }

        val createComment = commentService.createComment(
            comment = request.comment,
            tweatUuid = request.tweatUuid,
            userUuid = request.userUuid,
            parentCommentUuid = request.parentCommentUuid
        ) ?: throw ResourceException(300, "Create comment Error")

        return createComment

    }
    @PutMapping("/")
    fun updateComment(@RequestBody request: UpdateCommentRequest) : CommentDto{
        val comment  = commentService.findByUUid(request.uuid)
            ?: throw ResourceException(404, "Comment not found")

        return commentService.updateComment(
            msg = request.comment, commentDto = comment
        )!!
    }

}