package com.auth.template.auth.user

class UserTokenResult(
    val accessToken: String,
    val accessTokenExpiredIn: Long,
    val refreshToken: String,
    val refreshTokenExpiredIn: Long,
)
