package com.auth.template.auth.infra.domain.oauth.google

import com.auth.template.auth.domain.domain.oauth.google.GoogleProfile
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestHeader

@FeignClient(name = "googleProfileClient", url = "https://www.googleapis.com")
interface GoogleProfileClient {
    @GetMapping("/userinfo/v2/me")
    fun getProfile(
        @RequestHeader("Authorization") accessToken: String,
    ): GoogleProfile
}
