package com.auth.template.global

import java.time.LocalDateTime

class ErrorResponse(
    val timestamp: LocalDateTime,
    val status: Int,
    val code: String,
)
