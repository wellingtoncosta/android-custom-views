package io.github.wellingtoncosta.customviews.domain.exception

import java.lang.RuntimeException

class ServerFailureException : RuntimeException("A server-side error occurred.")
