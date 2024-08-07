package com.auth.template.auth.infra.domain.oauth.naver

import com.auth.template.auth.domain.domain.oauth.naver.NaverProfile
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestHeader

@FeignClient(name = "naverProfileClient", url = "https://openapi.naver.com")
interface NaverProfileClient {
    @GetMapping("/v1/nid/me")
    fun getProfile(
        @RequestHeader("Authorization") authorization: String, // Bearer <token>의 형태여야만 함
    ): NaverProfile
}
