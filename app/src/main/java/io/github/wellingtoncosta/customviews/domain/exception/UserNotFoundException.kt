package io.github.wellingtoncosta.customviews.domain.exception

import java.lang.RuntimeException

class UserNotFoundException(userName: String) : RuntimeException("User $userName does not exist.")
