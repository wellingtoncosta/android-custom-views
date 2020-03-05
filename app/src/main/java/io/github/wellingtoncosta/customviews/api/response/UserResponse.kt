package io.github.wellingtoncosta.customviews.api.response

import io.github.wellingtoncosta.customviews.domain.entity.User
import kotlinx.serialization.Serializable

@Serializable data class UserResponse(
    val login: String = "",
    val name: String = "",
    val avatar_url: String = "",
    val public_repos: Int = 0,
    val followers: Int = 0,
    val following: Int = 0,
    val starred: Int = 0
)

fun UserResponse.toDomain() = User(
    userName = login,
    fullName = name,
    avatarUrl = avatar_url,
    repositories = public_repos,
    followers = followers,
    following = following,
    starred = starred
)
