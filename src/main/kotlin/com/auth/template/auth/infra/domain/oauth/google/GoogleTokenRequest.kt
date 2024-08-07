package com.auth.template.auth.infra.domain.oauth.google

import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming

@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy::class)
class GoogleTokenRequest(
    val code: String?,
    val clientId: String?,
    val clientSecret: String?,
    val grantType: String = "authorization_code",
    val redirectUri: String,
)
