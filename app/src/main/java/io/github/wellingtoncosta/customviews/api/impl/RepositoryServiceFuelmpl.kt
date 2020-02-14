package io.github.wellingtoncosta.customviews.api.impl

import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.coroutines.awaitStringResponse
import io.github.wellingtoncosta.customviews.BuildConfig.API_URL
import io.github.wellingtoncosta.customviews.api.RepositoryService
import io.github.wellingtoncosta.customviews.api.response.RepositoryResponse
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

class RepositoryServiceFuelmpl(private val json: Json) : RepositoryService {

    private val serializer = RepositoryResponse.serializer().list

    override suspend fun fetchAll(userName: String) = withContext(Dispatchers.IO) {
        Fuel.get("$API_URL/users/$userName/repos")
            .awaitStringResponse()
            .run {
                when (second.statusCode) {
                    HTTP_200 -> json.parse(serializer, third).map { it.toDomain() }
                    HTTP_404 -> throw UserNotFoundException(userName)
                    HTTP_500 -> throw ServerFailureException()
                    else -> throw NetworkException()
                }
            }
    }

}
