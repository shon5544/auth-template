package com.auth.template.auth.domain.domain.oauth

import com.auth.template.auth.domain.support.exception.UserNotFoundException
import com.auth.template.common.domain.user.User
import com.auth.template.common.domain.user.UserRepository
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
@Transactional(readOnly = true)
class UserReader(
    private val userRepository: UserRepository,
) {
    fun findBySocialId(socialId: String): User? = userRepository.findBySocialId(socialId)

    // TODO: 언젠가 리프레시 토큰을 이용한 토큰 갱신 기능 api에 사용하기.
    fun findByRefreshToken(
        refreshToken: String,
        action: UserAuthActionEnum,
    ): User {
        val user =
            userRepository.findByRefreshToken(refreshToken)
                ?: throw UserNotFoundException(action.actionName)

        return user
    }
}
