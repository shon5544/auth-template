package com.auth.template.auth.domain.domain.oauth

enum class UserAuthActionEnum(
    val actionName: String,
) {
    // FIXME: 추후 oauth 모듈 최종 완성시 검토 후 주석 삭제 or 삭제
    // REGISTER("회원 가입"),
    // LOGIN("로그인"),
    REFRESH_TOKEN("토큰 재발급"),
}
