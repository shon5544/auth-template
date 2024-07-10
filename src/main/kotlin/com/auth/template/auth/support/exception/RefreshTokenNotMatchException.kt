package com.auth.template.auth.support.exception

class RefreshTokenNotMatchException(
    action: String,
) : RuntimeException("$action 실패: 리프레시 토큰이 일치하지 않습니다.")
