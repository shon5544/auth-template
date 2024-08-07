package com.auth.template.common.support.security

import com.auth.template.common.domain.token.TokenAuthService
import com.auth.template.common.support.response.AuthResponseCode
import com.auth.template.common.support.response.ErrorResponse
import com.fasterxml.jackson.databind.ObjectMapper
import io.jsonwebtoken.ExpiredJwtException
import io.jsonwebtoken.MalformedJwtException
import io.jsonwebtoken.io.DecodingException
import io.jsonwebtoken.security.SignatureException
import jakarta.servlet.FilterChain
import jakarta.servlet.ServletException
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.web.filter.OncePerRequestFilter
import java.io.IOException
import java.time.LocalDateTime

class JwtAuthenticationFilter(
    private val objectMapper: ObjectMapper,
    private val tokenAuthService: TokenAuthService,
) : OncePerRequestFilter() {
    @Throws(IOException::class, ServletException::class)
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain,
    ) {
        try {
            val token: String? = request.getHeader("Authorization")
            tokenAuthService.authenticate(token)

            filterChain.doFilter(request, response)
        } catch (e: SignatureException) {
            sendErrorMessage(response, AuthResponseCode.AUTH_01)
        } catch (e: MalformedJwtException) {
            sendErrorMessage(response, AuthResponseCode.AUTH_02)
        } catch (e: DecodingException) {
            sendErrorMessage(response, AuthResponseCode.AUTH_03)
        } catch (e: ExpiredJwtException) {
            sendErrorMessage(response, AuthResponseCode.AUTH_04)
        }
    }

    @Throws(IOException::class)
    private fun sendErrorMessage(
        res: HttpServletResponse,
        error: String,
    ) {
        res.status = HttpServletResponse.SC_UNAUTHORIZED
        res.contentType = MediaType.APPLICATION_JSON.toString()
        res.characterEncoding = "UTF-8"

        val dto = ErrorResponse(LocalDateTime.now(), HttpStatus.FORBIDDEN.value(), error)
        val response = objectMapper.writeValueAsString(dto)

        res.writer.write(response)
    }
}
