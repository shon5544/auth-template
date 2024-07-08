package com.auth.template.auth.domain

class JwtToken(
    val value: String,
    val expiredIn: Long,
)
