package com.auth.template.auth.domain

import org.springframework.stereotype.Component

@Component
class TokenIssuer(
    private val tokenCreator: JwtTokenCreator,
) {
    fun issueUserTokens(email: String): Tokens {
        val roles: Set<UserRole> = hashSetOf(UserRole.ROLE_USER)

        val accessToken = tokenCreator.createAccessToken(email, roles)
        val refreshToken = tokenCreator.createRefreshToken()

        return Tokens(accessToken, refreshToken)
    }
}
