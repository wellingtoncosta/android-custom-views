package io.github.wellingtoncosta.customviews.presentation.users

import android.content.Context
import android.util.AttributeSet
import android.widget.RelativeLayout
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import io.github.wellingtoncosta.customviews.core.navigation.viewScope
import io.github.wellingtoncosta.customviews.databinding.ScreenUsersBinding
import io.github.wellingtoncosta.customviews.presentation.extension.viewModel
import io.github.wellingtoncosta.customviews.presentation.users.UsersUiStates.Success
import io.github.wellingtoncosta.customviews.presentation.viewmodel.UsersViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class UsersScreen : RelativeLayout {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    private lateinit var binding: ScreenUsersBinding

    private val viewModel by viewModel<UsersViewModel>()

    private val usersAdapter = UsersAdapter()

    override fun onFinishInflate() {
        super.onFinishInflate()
        binding = ScreenUsersBinding.bind(this)
        binding.usersRecyclerView.run {
            adapter = usersAdapter
            addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))
        }
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()

        viewModel.states
            .onEach{ newState -> onNewUiState(newState) }
            .launchIn(viewScope)

        viewModel.load()
    }

    private fun onNewUiState(newState: UsersUiStates) {
        if (newState !is Success) {
            return newState.invoke(binding)
        }

        usersAdapter.dataSource = newState.users
    }
}
