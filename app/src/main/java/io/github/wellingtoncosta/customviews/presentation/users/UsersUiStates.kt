package io.github.wellingtoncosta.customviews.presentation.users

import androidx.core.view.isVisible
import io.github.wellingtoncosta.customviews.databinding.ScreenUsersBinding
import io.github.wellingtoncosta.customviews.domain.entity.User

sealed class UsersUiStates {
    open operator fun invoke(viewBinding: ScreenUsersBinding) = Unit

    object Loading : UsersUiStates() {
        override fun invoke(viewBinding: ScreenUsersBinding) = with(viewBinding) {
            progress.isVisible = true
            usersRecyclerView.isVisible = false
        }
    }

    object FinishedLoading : UsersUiStates() {
        override fun invoke(viewBinding: ScreenUsersBinding) = with(viewBinding) {
            progress.isVisible = false
            usersRecyclerView.isVisible = true
        }
    }

    data class Success(val users: List<User>) : UsersUiStates()

    data class Failure(val error: Throwable) : UsersUiStates()
}