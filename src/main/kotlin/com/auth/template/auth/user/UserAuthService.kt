package com.auth.template.auth.user

import com.auth.template.auth.security.token.TokenIssuer
import com.auth.template.auth.security.token.TokenResult
import com.auth.template.auth.security.token.Tokens
import com.auth.template.auth.support.exception.RefreshTokenNotMatchException
import com.auth.template.auth.support.exception.UserAlreadyExistException
import com.auth.template.auth.support.exception.UserNotFoundException
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class UserAuthService(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder,
    private val tokenIssuer: TokenIssuer,
) {
    fun register(
        email: String,
        username: String,
        password: String,
    ): TokenResult {
        val isExistEmail = userRepository.existsByEmail(email)

        if (isExistEmail) {
            throw UserAlreadyExistException(action = "회원 가입")
        }

        val encodedPassword: String = passwordEncoder.encode(password)

        val tokens: Tokens = tokenIssuer.issueUserTokens(email)

        val toRegister =
            UserEntity(
                email = email,
                username = username,
                password = encodedPassword,
                refreshToken = tokens.refreshToken.value,
            )

        userRepository.save(toRegister)

        return TokenResult(
            accessToken = tokens.accessToken.value,
            accessTokenExpiredIn = tokens.accessToken.expiredIn,
            refreshToken = tokens.refreshToken.value,
            refreshTokenExpiredIn = tokens.refreshToken.expiredIn,
        )
    }

    fun login(
        email: String,
        password: String,
    ): TokenResult {
        val encodedPassword: String = passwordEncoder.encode(password)

        val user =
            userRepository.findByEmailAndPassword(email, encodedPassword)
                ?: throw UserNotFoundException(action = "로그인")

        val tokens: Tokens = tokenIssuer.issueUserTokens(user.email)
        user.refreshToken = tokens.refreshToken.value

        return TokenResult(
            accessToken = tokens.accessToken.value,
            accessTokenExpiredIn = tokens.accessToken.expiredIn,
            refreshToken = tokens.refreshToken.value,
            refreshTokenExpiredIn = tokens.refreshToken.expiredIn,
        )
    }

    fun refresh(refreshToken: String): TokenResult {
        val user =
            userRepository.findByRefreshToken(refreshToken)
                ?: throw UserNotFoundException(action = "토큰 재발급")

        if (user.refreshToken != refreshToken) {
            throw RefreshTokenNotMatchException(action = "토큰 재발급")
        }

        val tokens: Tokens = tokenIssuer.issueUserTokens(user.email)
        user.refreshToken = refreshToken

        return TokenResult(
            accessToken = tokens.accessToken.value,
            accessTokenExpiredIn = tokens.accessToken.expiredIn,
            refreshToken = tokens.refreshToken.value,
            refreshTokenExpiredIn = tokens.refreshToken.expiredIn,
        )
    }
}
