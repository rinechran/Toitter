package com.example.toitter.service

import com.example.toitter.dto.CommentDto
import com.example.toitter.dto.TweatDto
import com.example.toitter.dto.UserDto
import com.example.toitter.repository.CommentRepository
import com.example.toitter.repository.entity.CommentEntity
import com.example.toitter.repository.entity.TweatEntity
import com.example.toitter.repository.entity.UsersEntity
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class CommentService(
    val commentRepository: CommentRepository,
    val tweatService : TweatService,
    val userService: UserService
){
    fun mapCommentEntityToDto(commentEntity: CommentEntity): CommentDto {
        return CommentDto(
            tweat_uuid = commentEntity.tweatUuid.uuid!!,
            user_uuid = commentEntity.userUuid.uuid!!,
            comment = commentEntity.comment,
            uuid = commentEntity.uuid!!,
            parentCommentUuid = commentEntity.parentComment?.uuid,
            replies = listOf()
        )
    }

    fun getComment(uuid: UUID) : List<CommentDto> {

        val commentsEntity = commentRepository.findCommentTreeByTweatUuid(uuid)

        val commentDtoMap = commentsEntity.map { commentEntity ->
            commentEntity.uuid to mapCommentEntityToDto(commentEntity)
        }.toMap()

        val rootComments = mutableListOf<CommentDto>()
        commentDtoMap.values.forEach { commentDto ->
            if (commentDto.parentCommentUuid == null) {
                rootComments.add(commentDto)
            } else {
                commentDtoMap[commentDto.parentCommentUuid]?.let { parentDto ->
                    parentDto.replies = parentDto.replies.toMutableList().apply {
                        add(commentDto)
                    }
                }
            }
        }
        return rootComments
    }


    @Transactional
    fun createComment(comment : String, tweatUuid : TweatDto, userUuid  : UserDto, parentCommentUuid: UUID?): CommentDto? {
        var parentCommentEntity: CommentEntity? = null
        if (parentCommentUuid!=null){
            parentCommentEntity = commentRepository.findByUuid(parentCommentUuid)
            ?: return null
        }
        val userEntity = UsersEntity(userUuid.name,userUuid.email,userUuid.password)
        userEntity.uuid = userUuid.uuid;

        val tweatEntity = TweatEntity(userEntity,comment);

        val newComment = CommentEntity(
            userUuid = userEntity,
            tweatUuid = tweatEntity,
            comment = comment,
            parentComment = parentCommentEntity
        )

        commentRepository.save(newComment)

        return CommentDto(
            tweat_uuid = tweatEntity.uuid!! ,
            user_uuid = userUuid.uuid,
            comment = newComment.comment,
            parentCommentUuid = newComment.parentComment?.uuid,
            uuid = newComment.uuid!!,
        )
    }

    @Transactional
    fun updateComment(msg : String , commentDto : CommentDto): CommentDto? {
        val comment = commentRepository.findByUuid(commentDto.uuid)
            ?: return null
        comment.comment = msg;
        commentRepository.save(comment)
        return CommentDto(
            tweat_uuid = comment.tweatUuid.uuid!!,
            user_uuid = comment.userUuid.uuid!!,
            comment = comment.comment,
            parentCommentUuid = null,
            uuid = comment.uuid!!
        )
    }
    fun findByUUid(uuid: UUID) : CommentDto? {
        val comment  = commentRepository.findByUuid(uuid) ?: return null
        return CommentDto(
            tweat_uuid = comment.tweatUuid.uuid!!,
            user_uuid = comment.userUuid.uuid!!,
            comment = comment.comment,
            parentCommentUuid = comment.parentComment?.uuid,
            uuid = comment.uuid!!,
        )

    }

}