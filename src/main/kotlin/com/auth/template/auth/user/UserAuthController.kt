package com.auth.template.auth.user

import com.auth.template.auth.support.response.AuthResponseCode
import com.auth.template.global.GeneralResponse
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/user")
class UserAuthController(
    private val userAuthService: UserAuthService,
) {
    @PostMapping("/register")
    fun register(
        @RequestBody request: UserRegisterRequest,
    ): GeneralResponse<UserTokenResult> {
        val userTokenResult: UserTokenResult =
            userAuthService.register(
                request.email,
                request.username,
                request.password,
            )

        return GeneralResponse.of(AuthResponseCode.AUTH_00, userTokenResult)
    }

    @PostMapping("/login")
    fun login(
        @RequestBody request: UserLoginRequest,
    ): GeneralResponse<UserTokenResult> {
        val userTokenResult: UserTokenResult = userAuthService.login(request.email, request.password)

        return GeneralResponse.of(AuthResponseCode.AUTH_00, userTokenResult)
    }

    @PostMapping("/refresh")
    fun refresh(
        @RequestBody request: TokenRefreshRequest,
    ): GeneralResponse<UserTokenResult> {
        val userTokenResult: UserTokenResult = userAuthService.refresh(request.refreshToken)

        return GeneralResponse.of(AuthResponseCode.AUTH_00, userTokenResult)
    }
}
