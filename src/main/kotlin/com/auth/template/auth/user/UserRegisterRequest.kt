package com.auth.template.auth.user

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

class UserRegisterRequest(
    @Email(message = "email 형식에 맞게 넣어주세요.")
    @NotNull(message = "email은 null 되어선 안 됩니다.")
    val email: String,
    // @Size(min = , max = )
    @NotBlank(message = "username은 null이거나 빈 문자열이어선 안 됩니다.")
    val username: String,
    // @Size(min = , max = )
    @NotBlank(message = "password는 null이거나 빈 문자열이어선 안 됩니다.")
    val password: String,
)
