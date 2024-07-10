package com.auth.template.auth.user

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "users")
class UserEntity(
    // 필드, 어노테이션 속상 값은 필요에 따라 사용하세요.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    @Column(unique = true)
    val email: String,
    val username: String,
    val password: String,
    var refreshToken: String = "default",
)
