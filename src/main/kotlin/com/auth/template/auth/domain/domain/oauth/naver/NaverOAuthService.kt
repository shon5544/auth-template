package com.auth.template.auth.domain.domain.oauth.naver

import com.auth.template.auth.domain.domain.oauth.UserReader
import com.auth.template.auth.domain.domain.oauth.UserWriter
import com.auth.template.auth.domain.domain.token.TokenIssuer
import com.auth.template.auth.domain.domain.token.UserToken
import com.auth.template.auth.domain.domain.token.UserTokenResult
import com.auth.template.common.domain.user.SocialInfoEnum
import org.springframework.stereotype.Service

@Service
class NaverOAuthService(
    private val userWriter: UserWriter,
    private val naverProfileReader: NaverProfileReader,
    private val userReader: UserReader,
    private val tokenIssuer: TokenIssuer,
) {
    fun auth(
        code: String?,
        state: String?,
    ): UserTokenResult {
        val profile: NaverProfileResponse = naverProfileReader.getProfile(code, state)

        val user =
            userReader.findBySocialId(profile.id!!)
                ?: return userWriter
                    .register(
                        socialId = profile.id,
                        socialType = SocialInfoEnum.NAVER,
                        email = profile.email!!,
                        nickname = profile.nickname!!,
                    ).toResult()

        val userToken: UserToken = tokenIssuer.issueUserTokens(user.email)

        return userToken.toResult()
    }
}
