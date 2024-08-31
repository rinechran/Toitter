package com.example.toitter.service

import com.example.toitter.repository.TweatRepository
import com.example.toitter.repository.entity.TweatEntity
import org.springframework.stereotype.Service


@Service
class TweatService(
    private val tweatRepository: TweatRepository
) {

    // GetList 메서드: 트윗 목록을 가져오는 메서드
    fun getList(name: String?, msg: String?): List<TweatEntity> {
        return if (name != null || msg != null) {
            tweatRepository.findByNameContainingAndMsgContaining(name ?: "", msg ?: "")
        } else {
            tweatRepository.findAll()
        }
    }
    fun create(tweat: TweatEntity): TweatEntity {
        return tweatRepository.save(tweat)
    }
}