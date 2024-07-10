package com.auth.template.auth.support.exception

import com.auth.template.auth.support.response.AuthResponseCode
import com.auth.template.global.ErrorResponse
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
        ErrorResponse(HttpStatus.BAD_REQUEST, AuthResponseCode.AUTH_06)
}
