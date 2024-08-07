package com.auth.template.auth.application.domain.oauth

import com.auth.template.auth.domain.domain.token.UserTokenResult

data class UserTokenResponse(
    val accessToken: String,
    val accessTokenExpiredIn: Long,
    val refreshToken: String,
    val refreshTokenExpiredIn: Long,
) {
    companion object {
        fun of(tokenResult: UserTokenResult): UserTokenResponse =
            UserTokenResponse(
                accessToken = tokenResult.accessToken,
                accessTokenExpiredIn = tokenResult.accessTokenExpiredIn,
                refreshToken = tokenResult.refreshToken,
                refreshTokenExpiredIn = tokenResult.refreshTokenExpiredIn,
            )
    }
}
