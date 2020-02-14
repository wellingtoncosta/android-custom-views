package io.github.wellingtoncosta.customviews.api.impl

import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.core.ResponseOf
import com.github.kittinunf.fuel.coroutines.awaitStringResponse
import io.github.wellingtoncosta.customviews.BuildConfig.API_URL
import io.github.wellingtoncosta.customviews.api.UserService
import io.github.wellingtoncosta.customviews.api.response.UserResponse
import io.github.wellingtoncosta.customviews.api.response.toDomain
import io.github.wellingtoncosta.customviews.api.util.HTTP_200
import io.github.wellingtoncosta.customviews.api.util.HTTP_404
import io.github.wellingtoncosta.customviews.api.util.HTTP_500
import io.github.wellingtoncosta.customviews.domain.exception.NetworkException
import io.github.wellingtoncosta.customviews.domain.exception.ServerFailureException
import io.github.wellingtoncosta.customviews.domain.exception.UserNotFoundException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json
import kotlinx.serialization.list

class UserServiceFuelImpl(
    private val fuel: Fuel,
    private val json: Json
) : UserService {

    private val serializer = UserResponse.serializer()

    override suspend fun fetchAll() = withContext(Dispatchers.IO) {
        fuel.get("$API_URL/users")
            .awaitStringResponse()
            .run {
                when (second.statusCode) {
                    HTTP_200 -> json.parse(serializer.list, third).map { it.toDomain() }
                    HTTP_500 -> throw ServerFailureException()
                    else -> throw NetworkException()
                }
            }
    }

    override suspend fun fetchOne(userName: String) = withContext(Dispatchers.IO) {
        fuel.get("$API_URL/users/$userName")
            .awaitStringResponse()
            .throwOrThen(userName) { response ->
                json.parse(serializer, response).toDomain()
            }
    }

    override suspend fun fetchFollowers(userName: String) = withContext(Dispatchers.IO) {
        fuel.get("$API_URL/users/$userName/followers")
            .awaitStringResponse()
            .throwOrThen(userName) { response ->
                json.parse(serializer.list, response).map { it.toDomain() }
            }
    }

    override suspend fun fetchFollowing(userName: String) = withContext(Dispatchers.IO) {
        fuel.get("$API_URL/users/$userName/following")
            .awaitStringResponse()
            .throwOrThen(userName) { response ->
                json.parse(serializer.list, response).map { it.toDomain() }
            }
    }

    private fun <T> ResponseOf<String>.throwOrThen(
        userName: String,
        onSuccess: (String) -> T
    ) = when (second.statusCode) {
        HTTP_200 -> onSuccess(third)
        HTTP_404 -> throw UserNotFoundException(userName)
        HTTP_500 -> throw ServerFailureException()
        else -> throw NetworkException()
    }

}