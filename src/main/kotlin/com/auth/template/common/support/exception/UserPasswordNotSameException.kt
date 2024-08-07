package com.auth.template.common.support.exception

class UserPasswordNotSameException(
    action: String,
) : RuntimeException("$action 실패: 비밀번호가 다릅니다.")
