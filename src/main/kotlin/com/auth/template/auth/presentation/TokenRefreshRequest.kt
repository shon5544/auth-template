package com.auth.template.auth.presentation

import jakarta.validation.constraints.NotBlank

class TokenRefreshRequest(
    @NotBlank(message = "refreshToken은 null 혹은 빈 문자열이어선 안 됩니다.")
    val refreshToken: String,
)
