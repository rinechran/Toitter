package com.example.toitter.repository.entity

import jakarta.persistence.*


@Entity(name = "comment")
class CommentEntity(

    @ManyToOne
    @JoinColumn(name ="user_uuid" , referencedColumnName = "uuid")
    val userUuid : UsersEntity,

    @ManyToOne
    @JoinColumn(name ="tweat_uuid" , referencedColumnName = "uuid")
    val tweatUuid : TweatEntity,

    @ManyToOne
    @JoinColumn(name = "parent_comment_uuid", referencedColumnName = "uuid")
    val parentComment: CommentEntity? = null,

    @OneToMany(mappedBy = "parentComment" )
    val replies: List<CommentEntity> = mutableListOf(),

    @Column
    var comment : String,


    ): BaseEntity(){

}