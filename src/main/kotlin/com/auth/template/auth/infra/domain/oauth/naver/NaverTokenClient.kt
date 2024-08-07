package com.auth.template.auth.infra.domain.oauth.naver

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam

@FeignClient(value = "naverTokenClient", url = "https://nid.naver.com")
interface NaverTokenClient {
    @GetMapping("/oauth2.0/token")
    fun getToken(
        @RequestParam("grant_type") grantType: String,
        @RequestParam("client_id") clientId: String,
        @RequestParam("client_secret") clientSecret: String,
        @RequestParam("code") code: String? = null, // 발급 시 필수
        @RequestParam("state") state: String? = null, // 발급 시 필수
        @RequestParam("refresh_token") refreshToken: String? = null, // 갱신 시 필수
        @RequestParam("access_token") accessToken: String? = null, // 삭제 시 필수
        @RequestParam("service_provider") serviceProvider: String? = null, // 삭제 시 필수; 기본 값 'NAVER'
    ): NaverTokenResponse
}
