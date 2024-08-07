package com.auth.template.auth.application.support.handler

import com.auth.template.auth.domain.support.exception.AuthorizationCodeNullException
import com.auth.template.auth.domain.support.exception.ClaimNotFoundException
import com.auth.template.auth.domain.support.exception.RefreshTokenNotMatchException
import com.auth.template.auth.domain.support.exception.UserAlreadyExistException
import com.auth.template.auth.domain.support.exception.UserNotFoundException
import com.auth.template.auth.domain.support.exception.UserPasswordNotSameException
import com.auth.template.common.support.response.AuthResponseCode
import com.auth.template.common.support.response.ErrorResponse
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class AuthExceptionHandler {
    @ExceptionHandler(RefreshTokenNotMatchException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleRefreshTokenNotMatchException(exception: RefreshTokenNotMatchException): ErrorResponse =
        ErrorResponse(HttpStatus.BAD_REQUEST, AuthResponseCode.AUTH_01)

    @ExceptionHandler(UserNotFoundException::class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    fun handleUserNotFoundException(exception: UserNotFoundException): ErrorResponse =
        ErrorResponse(HttpStatus.NOT_FOUND, AuthResponseCode.AUTH_05)

    @ExceptionHandler(UserAlreadyExistException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleUserAlreadyExistException(exception: UserAlreadyExistException): ErrorResponse =
        ErrorResponse(HttpStatus.BAD_REQUEST, AuthResponseCode.AUTH_06, exception.message)

    @ExceptionHandler(UserPasswordNotSameException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleUserPasswordNotSameException(exception: UserPasswordNotSameException): ErrorResponse =
        ErrorResponse(HttpStatus.BAD_REQUEST, AuthResponseCode.AUTH_07, exception.message)

    @ExceptionHandler(AuthorizationCodeNullException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleAuthorizationCodeNullException(exception: AuthorizationCodeNullException): ErrorResponse =
        ErrorResponse(HttpStatus.BAD_REQUEST, AuthResponseCode.AUTH_07, exception.message)

    @ExceptionHandler(ClaimNotFoundException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleClaimNotFoundException(exception: ClaimNotFoundException): ErrorResponse =
        ErrorResponse(HttpStatus.BAD_REQUEST, AuthResponseCode.AUTH_08, exception.message)
}
