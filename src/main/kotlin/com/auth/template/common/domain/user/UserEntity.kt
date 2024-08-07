package com.auth.template.common.domain.user

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "users")
class UserEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    @Column(unique = true)
    val email: String,
    val nickname: String,
    @Column(name = "refresh_token")
    var refreshToken: String = "default",
    @Column(name = "total_point")
    var totalPoint: Long = 0L,
    @Column(name = "profile_image_url")
    var profileImageUrl: String,
    @Enumerated(EnumType.STRING)
    var role: UserRoleEnum = UserRoleEnum.ROLE_USER,
    @Column(name = "is_deleted")
    var isDeleted: Boolean = false,
    @Enumerated(EnumType.STRING)
    val socialInfo: SocialInfoEnum,
    val socialId: String,
) {
    companion object {
        fun of(user: User): UserEntity =
            UserEntity(
                id = user.id,
                email = user.email,
                nickname = user.nickname,
                refreshToken = user.refreshToken,
                socialInfo = user.socialInfoEnum,
                socialId = user.socialId,
                profileImageUrl = user.profileImage,
            )
    }

    fun toDomain(): User =
        User(
            id = id,
            email = email,
            nickname = nickname,
            refreshToken = refreshToken,
            socialInfoEnum = socialInfo,
            socialId = socialId,
            profileImage = profileImageUrl,
        )
}
