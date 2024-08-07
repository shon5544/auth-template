package com.auth.template.auth.domain.domain.oauth.google

import com.auth.template.auth.domain.support.exception.AuthorizationCodeNullException
import com.auth.template.auth.infra.domain.oauth.google.GoogleProfileClient
import com.auth.template.auth.infra.domain.oauth.google.GoogleTokenClient
import com.auth.template.auth.infra.domain.oauth.google.GoogleTokenRequest
import com.auth.template.auth.infra.domain.oauth.google.GoogleTokenResponse
import com.auth.template.common.domain.user.SocialInfoEnum
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class GoogleProfileReader(
    private val googleTokenClient: GoogleTokenClient,
    private val googleProfileClient: GoogleProfileClient,
    @Value("\${oauth.google.client-id}")
    private val clientId: String,
    @Value("\${oauth.google.client-secret}")
    private val clientSecret: String,
    @Value("\${oauth.google.redirect-uri}")
    private val redirectUri: String,
) {
    fun getProfile(code: String?): GoogleProfile {
        if (code == null) {
            throw AuthorizationCodeNullException(SocialInfoEnum.GOOGLE.value)
        }

        val tokenRequest =
            GoogleTokenRequest(
                code = code,
                clientId = clientId,
                clientSecret = clientSecret,
                redirectUri = redirectUri,
            )

        val oAuthTokens: GoogleTokenResponse = googleTokenClient.getToken(tokenRequest)

        val accessToken = "Bearer " + oAuthTokens.accessToken!!

        return googleProfileClient.getProfile(accessToken)
    }
}
