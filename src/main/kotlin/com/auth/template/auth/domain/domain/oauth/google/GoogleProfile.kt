package com.auth.template.auth.domain.domain.oauth.google

import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming

@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy::class)
class GoogleProfile(
    val id: String?,
    val email: String?,
    val verifiedEmail: String?,
    val name: String?,
    val givenName: String?,
    val familyName: String?,
)
