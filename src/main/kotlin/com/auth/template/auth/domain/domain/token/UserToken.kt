package com.auth.template.auth.domain.domain.token

class UserToken(
    val accessToken: JwtToken,
    val refreshToken: JwtToken,
) {
    fun toResult(): UserTokenResult =
        UserTokenResult(
            accessToken = accessToken.value,
            accessTokenExpiredIn = accessToken.expiredIn,
            refreshToken = refreshToken.value,
            refreshTokenExpiredIn = refreshToken.expiredIn,
        )
}
