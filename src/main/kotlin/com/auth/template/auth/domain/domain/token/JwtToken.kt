package com.auth.template.auth.domain.domain.token

class JwtToken(
    val value: String,
    val expiredIn: Long,
)
