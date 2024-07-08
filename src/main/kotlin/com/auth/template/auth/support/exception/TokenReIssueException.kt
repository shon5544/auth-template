package com.auth.template.auth.support.exception

class TokenReIssueException : RuntimeException("토큰 재발행 실패: 리프레시 토큰이 일치하지 않습니다.")
