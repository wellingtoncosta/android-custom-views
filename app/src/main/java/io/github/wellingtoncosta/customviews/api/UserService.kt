package io.github.wellingtoncosta.customviews.api

import io.github.wellingtoncosta.customviews.domain.entity.User

interface UserService {

    suspend fun fetchAll(): List<User>

    suspend fun fetchOne(userName: String): User

    suspend fun fetchFollowers(userName: String): List<User>

    suspend fun fetchFollowing(userName: String): List<User>

}
