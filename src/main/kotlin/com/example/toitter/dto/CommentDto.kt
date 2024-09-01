package com.example.toitter.dto

import java.util.*

data class CommentDto (
    var tweat_uuid : UUID,
    var user_uuid : UUID,
    var comment : String,
    var parentCommentUuid : UUID? = null,
    var uuid : UUID,
    var replies: List<CommentDto> = listOf()
)