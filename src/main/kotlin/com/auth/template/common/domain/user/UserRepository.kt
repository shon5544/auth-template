package com.auth.template.common.domain.user

interface UserRepository {
    fun existsByEmail(email: String): Boolean

    fun findByEmail(email: String): User?

    fun findBySocialId(socialId: String): User?

    fun findByRefreshToken(refreshToken: String): User?

    fun save(user: User): User
}
