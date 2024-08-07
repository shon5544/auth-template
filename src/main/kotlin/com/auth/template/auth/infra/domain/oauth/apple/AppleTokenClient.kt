package com.auth.template.auth.infra.domain.oauth.apple

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam

@FeignClient(name = "appleTokenClient", url = "https://appleid.apple.com")
interface AppleTokenClient {
    @PostMapping("/auth/token?grant_type=authorization_code")
    fun getToken(
        @RequestParam("client_id") clientId: String?,
        @RequestParam("redirect_uri") redirectUri: String?,
        @RequestParam("code") code: String?,
        @RequestParam("client_secret") clientSecret: String?,
    ): AppleTokenResponse
}
