package com.auth.template.auth.infra.domain.oauth.google

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestBody

@FeignClient(name = "googleTokenClient", url = "https://oauth2.googleapis.com")
interface GoogleTokenClient {
    @GetMapping("/token")
    fun getToken(
        @RequestBody request: GoogleTokenRequest,
    ): GoogleTokenResponse
}
