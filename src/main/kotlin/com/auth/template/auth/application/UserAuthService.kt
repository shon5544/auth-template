package com.auth.template.auth.application

import com.auth.template.auth.domain.TokenIssuer
import com.auth.template.auth.domain.Tokens
import com.auth.template.auth.persistence.UserEntity
import com.auth.template.auth.persistence.UserRepository
import com.auth.template.auth.support.exception.TokenReIssueException
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
            userRepository.findByEmailAndPasswordOrNull(email, encodedPassword)
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
            throw TokenReIssueException()
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
