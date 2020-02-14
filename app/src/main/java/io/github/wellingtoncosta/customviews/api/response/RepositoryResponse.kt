package io.github.wellingtoncosta.customviews.api.response

import io.github.wellingtoncosta.customviews.domain.entity.Repository
import kotlinx.serialization.Serializable

@Serializable data class RepositoryResponse(
    val name: String,
    val description: String?,
    val forks_count: Int,
    val open_issues_count: Int,
    val stargazers_count: Int,
    val watchers: Int
)

fun RepositoryResponse.toDomain() = Repository(
    name = name,
    description = description,
    forks = forks_count,
    issues = open_issues_count,
    stars = stargazers_count,
    watchers = watchers
)
