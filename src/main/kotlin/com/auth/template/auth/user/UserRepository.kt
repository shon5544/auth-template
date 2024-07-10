package com.auth.template.auth.user

import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<UserEntity, Long> {
    fun existsByEmail(email: String): Boolean

    fun findByEmailAndPassword(
        email: String,
        password: String,
    ): UserEntity?

    fun findByRefreshToken(refreshToken: String): UserEntity?
}
