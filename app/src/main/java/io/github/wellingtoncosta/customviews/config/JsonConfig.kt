package io.github.wellingtoncosta.customviews.config

import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration

object JsonConfig {

    val json = Json(JsonConfiguration.Stable.copy(ignoreUnknownKeys = true))

}
