package com.auth.template.auth.application.domain.oauth

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotBlank

@Schema(name = "토큰 갱신 요청 API request body")
class TokenRefreshRequest(
    @NotBlank(message = "refreshToken은 null 혹은 빈 문자열이어선 안 됩니다.")
    val refreshToken: String,
)
