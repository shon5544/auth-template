package com.auth.template.auth.domain.domain.oauth

import com.auth.template.auth.domain.domain.token.TokenIssuer
import com.auth.template.auth.domain.domain.token.UserToken
import com.auth.template.common.domain.user.SocialInfoEnum
import com.auth.template.common.domain.user.User
import com.auth.template.common.domain.user.UserRepository
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
@Transactional
class UserWriter(
    private val userRepository: UserRepository,
    private val tokenIssuer: TokenIssuer,
    // FIXME: 언젠가 s3 전용 서비스 만들어서 교체
    @Value("\${s3.default-image-url}")
    private val defaultImage: String,
) {
    fun update(user: User) {
        userRepository.save(user)
    }

    // TODO: 프로필 정보 사진도 같이 받아와야하긴 함.
    fun register(
        socialId: String,
        socialType: SocialInfoEnum,
        email: String,
        nickname: String,
    ): UserToken {
        val userUserToken: UserToken = tokenIssuer.issueUserTokens(email)

        val toSave =
            User(
                email = email,
                nickname = nickname,
                socialInfoEnum = socialType,
                refreshToken = userUserToken.refreshToken.value,
                socialId = socialId,
                profileImage = defaultImage,
            )

        userRepository.save(toSave)

        return userUserToken
    }
}
