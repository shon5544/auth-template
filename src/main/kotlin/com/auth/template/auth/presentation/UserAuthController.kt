package com.auth.template.auth.presentation

import com.auth.template.auth.application.TokenResult
import com.auth.template.auth.application.UserAuthService
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
    ): GeneralResponse<TokenResult> {
        val tokenResult: TokenResult =
            userAuthService.register(
                request.email,
                request.username,
                request.password,
            )

        return GeneralResponse.of(AuthResponseCode.AUTH_00, tokenResult)
    }

    @PostMapping("/login")
    fun login(
        @RequestBody request: UserLoginRequest,
    ): GeneralResponse<TokenResult> {
        val tokenResult: TokenResult = userAuthService.login(request.email, request.password)

        return GeneralResponse.of(AuthResponseCode.AUTH_00, tokenResult)
    }

    @PostMapping("/refresh")
    fun refresh(
        @RequestBody request: TokenRefreshRequest,
    ): GeneralResponse<TokenResult> {
        val tokenResult: TokenResult = userAuthService.refresh(request.refreshToken)

        return GeneralResponse.of(AuthResponseCode.AUTH_00, tokenResult)
    }
}
