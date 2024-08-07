package com.auth.template.auth.domain.domain.token

import com.auth.template.common.domain.user.UserRoleEnum
import org.springframework.stereotype.Component

@Component
class TokenIssuer(
    private val tokenCreator: JwtTokenCreator,
) {
    fun issueUserTokens(email: String): UserToken {
        val roles: Set<UserRoleEnum> = hashSetOf(UserRoleEnum.ROLE_USER)

        val accessToken = tokenCreator.createAccessToken(email, roles)
        val refreshToken = tokenCreator.createRefreshToken()

        return UserToken(accessToken, refreshToken)
    }
}
