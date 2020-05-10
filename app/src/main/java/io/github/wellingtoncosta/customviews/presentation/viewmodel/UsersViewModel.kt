package io.github.wellingtoncosta.customviews.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.github.wellingtoncosta.customviews.api.UserService
import io.github.wellingtoncosta.customviews.di.ViewScope
import io.github.wellingtoncosta.customviews.domain.entity.User
import io.github.wellingtoncosta.customviews.presentation.users.UsersUiStates
import kotlinx.coroutines.channels.ConflatedBroadcastChannel
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@ViewScope
class UsersViewModel @Inject constructor(
    private val service: UserService
) : ViewModel() {
    private val broadcast = ConflatedBroadcastChannel<UsersUiStates>()

    val states = broadcast.asFlow()

    fun load() {
        viewModelScope.launch {
            push(UsersUiStates.Loading)
            try {
                val user = service.fetchAll()
                push(UsersUiStates.Success(user))
            } catch (exception: Throwable) {
                push(UsersUiStates.Failure(exception))
            } finally {
                push(UsersUiStates.FinishedLoading)
            }
        }
    }

    private suspend fun push(state: UsersUiStates) = broadcast.send(state)
}
