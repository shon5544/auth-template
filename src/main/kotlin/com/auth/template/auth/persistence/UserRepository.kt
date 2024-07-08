package com.auth.template.auth.persistence

import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<UserEntity, Long> {
    fun findByEmailOrNull(email: String): UserEntity?

    fun findByEmailAndPasswordOrNull(
        email: String,
        password: String,
    ): UserEntity?

    fun findByRefreshToken(refreshToken: String): UserEntity?
}
