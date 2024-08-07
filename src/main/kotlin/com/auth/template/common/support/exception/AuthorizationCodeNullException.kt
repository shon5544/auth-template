package com.auth.template.common.support.exception

class AuthorizationCodeNullException(
    platform: String,
) : RuntimeException("$platform 토큰 발급 실패: auth_code가 null이어선 안 됩니다.")
