package com.auth.template.auth.application

class TokenResult(
    val accessToken: String,
    val accessTokenExpiredIn: Long,
    val refreshToken: String,
    val refreshTokenExpiredIn: Long,
)
