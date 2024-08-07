package com.auth.template.auth.domain.domain.oauth.google

import com.auth.template.auth.domain.domain.oauth.UserReader
import com.auth.template.auth.domain.domain.oauth.UserWriter
import com.auth.template.auth.domain.domain.token.TokenIssuer
import com.auth.template.auth.domain.domain.token.UserToken
import com.auth.template.auth.domain.domain.token.UserTokenResult
import com.auth.template.common.domain.user.SocialInfoEnum
import org.springframework.stereotype.Service

@Service
class GoogleOAuthService(
    private val googleProfileReader: GoogleProfileReader,
    private val userReader: UserReader,
    private val userWriter: UserWriter,
    private val tokenIssuer: TokenIssuer,
) {
    fun auth(code: String?): UserTokenResult {
        val profile: GoogleProfile = googleProfileReader.getProfile(code)

        val user =
            userReader.findBySocialId(profile.id!!)
                ?: return userWriter
                    .register(
                        socialId = profile.id,
                        socialType = SocialInfoEnum.GOOGLE,
                        email = profile.email!!,
                        nickname = profile.name!!,
                    ).toResult()

        val userToken: UserToken = tokenIssuer.issueUserTokens(user.email)

        return userToken.toResult()
    }
}
