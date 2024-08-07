package com.auth.template.auth.infra.domain.oauth.google

import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming

@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy::class)
data class GoogleTokenResponse(
    val accessToken: String?,
    val expiresIn: String?,
    val scope: String?,
    val tokenType: String?,
    val idToken: String?,
)
