package com.auth.template.auth.domain.support.exception

class UserNotFoundException(
    action: String,
) : RuntimeException("$action 실패: 해당하는 유저를 찾을 수 없습니다.")
