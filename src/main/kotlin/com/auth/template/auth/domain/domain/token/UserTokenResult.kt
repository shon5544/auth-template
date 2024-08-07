package com.auth.template.auth.domain.domain.token

class UserTokenResult(
    val accessToken: String,
    val accessTokenExpiredIn: Long,
    val refreshToken: String,
    val refreshTokenExpiredIn: Long,
)
