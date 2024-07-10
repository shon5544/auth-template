package com.auth.template.auth.security.token

class JwtToken(
    val value: String,
    val expiredIn: Long,
)
