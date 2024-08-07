package com.auth.template.common.support.exception

class UserAlreadyExistException(
    action: String,
) : RuntimeException("$action 실패: 이미 유저가 존재합니다.")
