package com.auth.template.auth.application.domain.oauth

import com.auth.template.auth.domain.domain.oauth.apple.AppleOAuthService
import com.auth.template.auth.domain.domain.oauth.google.GoogleOAuthService
import com.auth.template.auth.domain.domain.oauth.naver.NaverOAuthService
import com.auth.template.common.support.response.AuthResponseCode
import com.auth.template.common.support.response.GeneralResponse
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.HttpStatus
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/auth")
@Tag(name = "🔐 OAuth 인증 API", description = "인증 관련 API")
@Validated
class UserOAuthController(
    private val naverOAuthService: NaverOAuthService,
    private val googleOAuthService: GoogleOAuthService,
    private val appleOAuthService: AppleOAuthService,
) {
    @Operation(
        summary = "네이버 로그인",
        description = "네이버로 로그인합니다.",
    )
    @GetMapping("/naver")
    @ResponseStatus(HttpStatus.OK)
    fun authNaver(
        @Parameter(description = "[필수] 네이버 로그인 후 받은 코드입니다.")
        @RequestParam code: String?,
        @Parameter(description = "[필수] 네이버 로그인 후 받은 상태 토큰입니다.")
        @RequestParam state: String?,
        @Parameter(description = "[선택] 네이버 로그인 오류입니다.")
        @RequestParam error: String?,
        @Parameter(description = "[선택] 네이버 로그인 오류 설명입니다.")
        @RequestParam("error_description") errorDescription: String?,
    ): GeneralResponse<UserTokenResponse> {
        val result = naverOAuthService.auth(code, state)

        val response = UserTokenResponse.of(result)
        return GeneralResponse.of(AuthResponseCode.AUTH_00, response)
    }

    @Operation(
        summary = "구글 로그인",
        description = "구글로 로그인합니다.",
    )
    @GetMapping("/google")
    @ResponseStatus(HttpStatus.OK)
    fun authGoogle(
        @RequestParam code: String?,
    ): GeneralResponse<UserTokenResponse> {
        val result = googleOAuthService.auth(code)

        val response = UserTokenResponse.of(result)
        return GeneralResponse.of(AuthResponseCode.AUTH_00, response)
    }

    @Operation(
        summary = "애플 로그인",
        description = "애플로 로그인합니다.",
    )
    @GetMapping("/apple")
    @ResponseStatus(HttpStatus.OK)
    fun authApple(
        @RequestParam code: String?,
    ): GeneralResponse<UserTokenResponse> {
        val result = appleOAuthService.auth(code)

        val response = UserTokenResponse.of(result)
        return GeneralResponse.of(AuthResponseCode.AUTH_00, response)
    }
}
