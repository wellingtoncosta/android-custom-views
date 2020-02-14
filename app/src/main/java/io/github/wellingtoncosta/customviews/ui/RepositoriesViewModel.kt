package io.github.wellingtoncosta.customviews.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.github.wellingtoncosta.customviews.api.RepositoryService
import io.github.wellingtoncosta.customviews.domain.entity.Repository
import kotlinx.coroutines.launch

class RepositoriesViewModel(private val service: RepositoryService) : ViewModel() {

    private val _repositories = MutableLiveData<List<Repository>>()
    private val _loading = MutableLiveData<Boolean>()
    private val _error = MutableLiveData<Throwable>()

    val repositories: LiveData<List<Repository>> get() = _repositories
    val loading: LiveData<Boolean> get() = _loading
    val error: LiveData<Throwable> get() = _error

    fun load(userName: String) {
        viewModelScope.launch {
            try {
                _loading.value = true
                _repositories.value = service.fetchAll(userName)
            } catch (exception: Throwable) {
                _error.value = exception
            } finally {
                _loading.value = false
            }
        }
    }

}
