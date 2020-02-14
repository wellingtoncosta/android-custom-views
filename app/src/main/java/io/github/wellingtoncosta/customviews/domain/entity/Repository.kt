package io.github.wellingtoncosta.customviews.domain.entity

data class Repository(
    val name: String,
    val description: String?,
    val stars: Int,
    val watchers: Int,
    val issues: Int
)
