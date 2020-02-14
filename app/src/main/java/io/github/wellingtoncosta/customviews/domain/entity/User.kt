package io.github.wellingtoncosta.customviews.domain.entity

data class User(
    val userName: String,
    val fullName: String?,
    val avatarUrl: String,
    val followers: Int,
    val following: Int,
    val starred: Int
)
