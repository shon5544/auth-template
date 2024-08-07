package com.auth.template.common.domain.user

class User(
    val id: Long? = null,
    var email: String,
    var nickname: String,
    var refreshToken: String = "default",
    val socialInfoEnum: SocialInfoEnum,
    val socialId: String,
    val profileImage: String,
)
