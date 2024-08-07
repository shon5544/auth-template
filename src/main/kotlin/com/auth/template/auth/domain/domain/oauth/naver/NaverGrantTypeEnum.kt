package com.auth.template.auth.domain.domain.oauth.naver

enum class NaverGrantTypeEnum(
    val value: String,
) {
    AUTH("authorization_code"),
    REFRESH("refresh_token"),
    DELETE("delete"),
}
