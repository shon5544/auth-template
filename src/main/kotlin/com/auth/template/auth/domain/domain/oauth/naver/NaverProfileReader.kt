package com.auth.template.auth.domain.domain.oauth.naver

import com.auth.template.auth.domain.support.exception.AuthorizationCodeNullException
import com.auth.template.auth.infra.domain.oauth.naver.NaverProfileClient
import com.auth.template.auth.infra.domain.oauth.naver.NaverTokenClient
import com.auth.template.auth.infra.domain.oauth.naver.NaverTokenResponse
import com.auth.template.common.domain.user.SocialInfoEnum
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class NaverProfileReader(
    private val naverTokenClient: NaverTokenClient,
    private val naverProfileClient: NaverProfileClient,
    @Value("\${oauth.naver.client-id}")
    private val clientId: String,
    @Value("\${oauth.naver.client-secret}")
    private val clientSecret: String,
) {
    fun getProfile(
        code: String?,
        state: String?,
    ): NaverProfileResponse {
        if (code == null) {
            throw AuthorizationCodeNullException(SocialInfoEnum.NAVER.value)
        }

        val tokens: NaverTokenResponse =
            naverTokenClient.getToken(
                NaverGrantTypeEnum.AUTH.value,
                clientId,
                clientSecret,
                code,
                state,
            )

        val authorization = "Bearer " + tokens.accessToken!!

        return naverProfileClient.getProfile(authorization).response!!
    }
}
