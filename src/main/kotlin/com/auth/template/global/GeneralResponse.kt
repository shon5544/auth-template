package com.auth.template.global

class GeneralResponse<T>(
    private val code: String,
    private val result: T?,
) {
    companion object {
        fun <T> of(
            code: String,
            result: T,
        ): GeneralResponse<T> = GeneralResponse(code, result)

        fun of(code: String): GeneralResponse<Void> = GeneralResponse(code, null)
    }
}
