package com.example.toitter.repository

import com.example.toitter.repository.entity.CommentEntity
import com.example.toitter.repository.entity.TweatEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import java.util.*

interface CommentRepository : JpaRepository<CommentEntity, Long>{
    fun findByUuid(uuid : UUID) : CommentEntity?
    @Query(value = """
    WITH RECURSIVE CommentTree AS (
        -- 최상위 댓글 (부모가 없는 댓글) 가져오기, create_data_at 기준으로 정렬
        SELECT 
            uuid,
            user_uuid,
            tweat_uuid,
            comment,
            parent_comment_uuid,
            is_delete,
            create_data_at,
            update_data_at,
            uuid AS root_uuid,
            create_data_at AS root_create_data_at
        FROM comment
        WHERE tweat_uuid = :tweatUuid
          AND parent_comment_uuid IS NULL

        UNION ALL

        -- 재귀적으로 자식 댓글 가져오기
        SELECT 
            c.uuid,
            c.user_uuid,
            c.tweat_uuid,
            c.comment,
            c.parent_comment_uuid,
            c.is_delete,
            c.create_data_at,
            c.update_data_at,
            ct.root_uuid,
            ct.root_create_data_at
        FROM comment c
        INNER JOIN CommentTree ct ON c.parent_comment_uuid = ct.uuid
    )

    SELECT * FROM CommentTree
    ORDER BY 
        root_create_data_at ASC, 
        parent_comment_uuid IS NOT NULL, 
        create_data_at ASC 
    """, nativeQuery = true)
    fun findCommentTreeByTweatUuid(@Param("tweatUuid") tweatUuid: UUID): List<CommentEntity>


}