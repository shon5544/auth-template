package com.auth.template.common.support.response // FIXME: 언젠가 글로벌 패키지에 옮겨야 함.

class GeneralResponse<T>(
    val code: String,
    val result: T?,
) {
    companion object {
        fun <T> of(
            code: String,
            result: T,
        ): GeneralResponse<T> = GeneralResponse(code, result)

        fun of(code: String): GeneralResponse<Void> = GeneralResponse(code, null)
    }
}
