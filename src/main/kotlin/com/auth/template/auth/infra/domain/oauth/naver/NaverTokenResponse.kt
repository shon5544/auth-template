package com.auth.template.auth.infra.domain.oauth.naver

import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming

@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy::class)
data class NaverTokenResponse(
    val accessToken: String?,
    val refreshToken: String?,
    val tokenType: String?,
    val expiresIn: Int?,
    val error: String?,
    val errorDescription: String?,
)
