package com.example.toitter.repository

import com.example.toitter.repository.entity.QTweatEntity.tweatEntity
import com.example.toitter.repository.entity.QUsersEntity.usersEntity
import com.example.toitter.repository.entity.TweatEntity
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.stereotype.Component
import org.springframework.stereotype.Repository


interface TweatCustomRepository {
    fun findByUsersEntity_NameContainingAndMsgContainingAndIsDeleteFalseOrderByCreatedAtAsc(
        name: String?,
        msg: String?
    ): List<TweatEntity>
}

@Repository
class TweatCustomRepositoryImpl(
    private val queryFactory: JPAQueryFactory
) : TweatCustomRepository {

     override fun findByUsersEntity_NameContainingAndMsgContainingAndIsDeleteFalseOrderByCreatedAtAsc(
         name: String?,
         msg: String?
    ): List<TweatEntity>{

         val nameCondition = name?.let { usersEntity.name.eq(it) }
         val msgCondition = msg?.let { tweatEntity.msg.containsIgnoreCase(it) }

         val combinedCondition = listOfNotNull(nameCondition, msgCondition)
             .fold(tweatEntity.isDelete.isFalse) { acc, condition -> acc.and(condition) }

         var query = queryFactory
             .selectFrom(tweatEntity)

         if (nameCondition != null) {
             query = query.join(usersEntity).on(tweatEntity.usersEntity.eq(usersEntity))
         }

         return query
             .where(combinedCondition)
             .orderBy(tweatEntity.createdAt.asc())
             .fetch()
    }
}