package com.auth.template.auth.domain.domain.oauth.apple

import com.auth.template.auth.domain.support.exception.AuthorizationCodeNullException
import com.auth.template.auth.infra.domain.oauth.apple.AppleTokenClient
import com.auth.template.common.domain.user.SocialInfoEnum
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import io.jsonwebtoken.Jwts
import org.bouncycastle.asn1.pkcs.PrivateKeyInfo
import org.bouncycastle.jce.provider.BouncyCastleProvider
import org.bouncycastle.openssl.jcajce.JcaPEMKeyConverter
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import java.security.PrivateKey
import java.security.Security
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.Base64
import java.util.Date

@Component
class AppleIdTokenGetter(
    private val appleTokenClient: AppleTokenClient,
    @Value("\${oauth.apple.client-id}")
    private val clientId: String,
    @Value("\${oauth.apple.key-id}")
    private val keyId: String,
    @Value("\${oauth.apple.redirect-uri}")
    private val redirectUri: String,
    @Value("\${oauth.apple.audience}")
    private val audience: String,
    @Value("\${oauth.apple.team-id}")
    private val teamId: String,
    @Value("\${oauth.apple.private-key}")
    private val privateKey: String,
) {
    private val decoder: Base64.Decoder = Base64.getUrlDecoder()
    private val objectMapper: ObjectMapper =
        ObjectMapper()
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)

    companion object {
        const val KEY_ID = "kid"
    }

    @Transactional
    fun get(code: String?): AppleTokenPayload {
        if (code == null) {
            throw AuthorizationCodeNullException(SocialInfoEnum.GOOGLE.value)
        }

        val idToken: String =
            appleTokenClient
                .getToken(
                    clientId,
                    redirectUri,
                    generateClientSecret(),
                    code,
                ).idToken

        return getPayload(idToken)
    }

    private fun getPayload(token: String): AppleTokenPayload {
        val tokenParts: List<String> = token.split(".")
        val payloadJWT = tokenParts[1]
        val payload = String(decoder.decode(payloadJWT))

        return objectMapper.readValue(payload, AppleTokenPayload::class.java)
    }

    private fun generateClientSecret(): String {
        val expiration = LocalDateTime.now().plusMinutes(5)
        val privateKey = getPrivateKey()

        return Jwts
            .builder()
            .header()
            .empty()
            .add(KEY_ID, keyId)
            .and()
            .issuer(teamId)
            .audience()
            .add(audience)
            .and()
            .subject(clientId)
            .expiration(Date.from(expiration.atZone(ZoneId.systemDefault()).toInstant()))
            .issuedAt(Date())
            .signWith(privateKey, Jwts.SIG.ES256)
            .compact()
    }

    private fun getPrivateKey(): PrivateKey {
        Security.addProvider(BouncyCastleProvider())

        val converter: JcaPEMKeyConverter = JcaPEMKeyConverter().setProvider("BC")

        return try {
            val privateKeyBytes: ByteArray = Base64.getDecoder().decode(privateKey)

            val privateKeyInfo = PrivateKeyInfo.getInstance(privateKeyBytes)
            converter.getPrivateKey(privateKeyInfo)
        } catch (e: Exception) {
            throw RuntimeException("Error converting private key from String", e)
        }
    }
}
