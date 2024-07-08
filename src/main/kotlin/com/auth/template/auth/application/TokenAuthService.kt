package com.auth.template.auth.application

import com.auth.template.auth.domain.ClaimExtractor
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service
import java.util.stream.Collectors

@Service
class TokenAuthService(
    private val claimExtractor: ClaimExtractor,
) {
    fun authenticate(token: String?) {
        val claims = claimExtractor.extractAllClaims(token)

        if (claims != null) {
            val authorities: List<SimpleGrantedAuthority> =
                claims
                    .get("roles", List::class.java)
                    .stream()
                    .map { SimpleGrantedAuthority(it as String) }
                    .collect(Collectors.toList())

            SecurityContextHolder.getContext().authentication =
                UsernamePasswordAuthenticationToken(claims["email"], "", authorities)
        }
    }
}
