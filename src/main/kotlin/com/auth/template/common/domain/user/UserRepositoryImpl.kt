package com.auth.template.common.domain.user

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
class UserRepositoryImpl(
    private val userJpaRepository: UserJpaRepository,
) : UserRepository {
    override fun existsByEmail(email: String): Boolean = userJpaRepository.existsByEmail(email)

    override fun findByEmail(email: String): User? {
        val userEntity =
            userJpaRepository.findByEmail(email)
                ?: return null

        return userEntity.toDomain()
    }

    override fun findBySocialId(socialId: String): User? {
        val userEntity =
            userJpaRepository.findBySocialId(socialId)
                ?: return null

        return userEntity.toDomain()
    }

    override fun findByRefreshToken(refreshToken: String): User? {
        val userEntity =
            userJpaRepository.findByRefreshToken(refreshToken)
                ?: return null

        return userEntity.toDomain()
    }

    override fun save(user: User): User {
        val userEntity = UserEntity.of(user)

        val result = userJpaRepository.save(userEntity)

        return result.toDomain()
    }
}

interface UserJpaRepository : JpaRepository<UserEntity, Long> {
    fun existsByEmail(email: String): Boolean

    fun findByEmail(email: String): UserEntity?

    fun findBySocialId(socialId: String): UserEntity?

    fun findByRefreshToken(refreshToken: String): UserEntity?
}
