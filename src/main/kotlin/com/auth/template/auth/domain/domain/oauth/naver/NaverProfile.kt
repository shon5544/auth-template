package com.auth.template.auth.domain.domain.oauth.naver

data class NaverProfile(
    val resultCode: String?,
    val message: String?,
    val response: NaverProfileResponse?,
)

data class NaverProfileResponse(
    val id: String?,
    val nickname: String?,
    val email: String?,
)
