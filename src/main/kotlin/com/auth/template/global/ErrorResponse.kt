package com.auth.template.global

import org.springframework.http.HttpStatus
import java.time.LocalDateTime

class ErrorResponse(
    val timestamp: LocalDateTime,
    val status: Int,
    val code: String,
) {
    constructor(httpStatus: HttpStatus, errCode: String) : this(
        timestamp = LocalDateTime.now(),
        status = httpStatus.value(),
        code = errCode,
    )
}
