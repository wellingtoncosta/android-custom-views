package io.github.wellingtoncosta.customviews.domain.exception

import java.lang.RuntimeException

class NetworkException : RuntimeException("Could not make the http call due an unexpected error.")
