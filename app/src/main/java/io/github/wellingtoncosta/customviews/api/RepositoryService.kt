package io.github.wellingtoncosta.customviews.api

import io.github.wellingtoncosta.customviews.domain.entity.Repository

interface RepositoryService {

    suspend fun fetchAll(userName: String): List<Repository>

}
