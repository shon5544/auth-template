package com.auth.template.common.support.resolver

import com.auth.template.common.domain.token.ClaimExtractor
import com.auth.template.common.support.exception.ClaimNotFoundException
import com.auth.template.common.support.security.Auth
import com.auth.template.common.support.security.AuthInfo
import io.jsonwebtoken.Claims
import jakarta.servlet.http.HttpServletRequest
import org.springframework.core.MethodParameter
import org.springframework.stereotype.Component
import org.springframework.web.bind.support.WebDataBinderFactory
import org.springframework.web.context.request.NativeWebRequest
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.method.support.ModelAndViewContainer

@Component
class UserArgumentResolver(
    private val claimExtractor: ClaimExtractor,
) : HandlerMethodArgumentResolver {
    override fun supportsParameter(parameter: MethodParameter): Boolean =
        parameter.parameterType == AuthInfo::class.java &&
            parameter.hasParameterAnnotation(Auth::class.java)

    override fun resolveArgument(
        parameter: MethodParameter,
        mavContainer: ModelAndViewContainer?,
        webRequest: NativeWebRequest,
        binderFactory: WebDataBinderFactory?,
    ): Any? {
        val httpServletRequest: HttpServletRequest = webRequest.nativeRequest as HttpServletRequest

        val token: String = httpServletRequest.getHeader("Authorization")

        val claims: Claims =
            claimExtractor.extractAllClaims(token) ?: throw ClaimNotFoundException("인증 실패: 토큰에 claim이 없습니다.")

        val email = claims["email"] as String
        return AuthInfo(email)
    }
}
