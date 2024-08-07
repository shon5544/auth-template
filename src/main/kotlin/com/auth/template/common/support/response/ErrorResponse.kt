package com.auth.template.common.support.response // FIXME: 언젠가 글로벌 패키지에 옮겨야 함.

import org.springframework.http.HttpStatus
import java.time.LocalDateTime

class ErrorResponse(
    val timestamp: LocalDateTime,
    val status: Int,
    val code: String,
    val detail: String? = null,
) {
    constructor(httpStatus: HttpStatus, errCode: String, detail: String? = null) : this(
        timestamp = LocalDateTime.now(),
        status = httpStatus.value(),
        code = errCode,
        detail = detail,
    )
}
