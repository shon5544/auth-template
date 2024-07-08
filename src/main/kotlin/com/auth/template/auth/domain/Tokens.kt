package com.auth.template.auth.domain

class Tokens(
    val accessToken: JwtToken,
    val refreshToken: JwtToken,
)
