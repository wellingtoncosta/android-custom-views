package io.github.wellingtoncosta.customviews.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.github.wellingtoncosta.customviews.api.UserService
import io.github.wellingtoncosta.customviews.di.ViewScope
import io.github.wellingtoncosta.customviews.domain.entity.User
import kotlinx.coroutines.launch
import javax.inject.Inject

@ViewScope
class UserViewModel @Inject constructor(
    private val service: UserService
) : ViewModel() {

    private val _user = MutableLiveData<User>()
    private val _loading = MutableLiveData<Boolean>()
    private val _error = MutableLiveData<Throwable>()

    val user: LiveData<User> get() = _user
    val loading: LiveData<Boolean> get() = _loading
    val error: LiveData<Throwable> get() = _error

    fun load(username: String) {
        viewModelScope.launch {
            try {
                _loading.value = true
                _user.value = service.fetchOne(username)
            } catch (exception: Throwable) {
                _error.value = exception
            } finally {
                _loading.value = false
            }
        }
    }

}
