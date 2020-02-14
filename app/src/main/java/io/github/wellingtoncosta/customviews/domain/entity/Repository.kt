package io.github.wellingtoncosta.customviews.domain.entity

data class Repository(
    val name: String,
    val description: String?,
    val forks: Int,
    val issues: Int,
    val stars: Int,
    val watchers: Int
)
