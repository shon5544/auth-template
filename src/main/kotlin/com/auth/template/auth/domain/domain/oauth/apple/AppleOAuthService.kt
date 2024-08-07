package com.auth.template.auth.domain.domain.oauth.apple

import com.auth.template.auth.domain.domain.oauth.UserReader
import com.auth.template.auth.domain.domain.oauth.UserWriter
import com.auth.template.auth.domain.domain.token.TokenIssuer
import com.auth.template.auth.domain.domain.token.UserToken
import com.auth.template.auth.domain.domain.token.UserTokenResult
import com.auth.template.common.domain.user.SocialInfoEnum
import org.springframework.stereotype.Service

@Service
class AppleOAuthService(
    private val appleIdTokenGetter: AppleIdTokenGetter,
    private val userReader: UserReader,
    private val userWriter: UserWriter,
    private val tokenIssuer: TokenIssuer,
) {
    fun auth(code: String?): UserTokenResult {
        val tokenPayload: AppleTokenPayload = appleIdTokenGetter.get(code)

        val user =
            userReader.findBySocialId(tokenPayload.sub)
                ?: return userWriter
                    .register(
                        socialId = tokenPayload.sub,
                        socialType = SocialInfoEnum.APPLE,
                        email = tokenPayload.email,
                        nickname = "profile.name", // FIXME: 이름 생성 규칙이 있어야할 듯.
                    ).toResult()

        val userToken: UserToken = tokenIssuer.issueUserTokens(user.email)

        return userToken.toResult()
    }
}
