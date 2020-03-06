package io.github.wellingtoncosta.customviews.presentation.users

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.widget.RelativeLayout
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import io.github.wellingtoncosta.customviews.App
import io.github.wellingtoncosta.customviews.databinding.ScreenUsersBinding
import io.github.wellingtoncosta.customviews.domain.entity.User
import io.github.wellingtoncosta.customviews.presentation.UsersViewModel
import javax.inject.Inject

class UsersScreen : RelativeLayout {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    private lateinit var binding: ScreenUsersBinding

    @Inject lateinit var viewModel: UsersViewModel

    private val usersAdapter = UsersAdapter()

    private val loadingObserver = Observer<Boolean> { isLoading ->
        binding.loading.isLoading = isLoading
    }

    private val usersObserver = Observer<List<User>> { users ->
        usersAdapter.dataSource = users
    }

    override fun onFinishInflate() {
        super.onFinishInflate()
        binding = ScreenUsersBinding.bind(this)
        binding.users.run {
            adapter = usersAdapter
            addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))
        }
    }

    override fun onAttachedToWindow() {
        (context.applicationContext as App).appComponent.usersComponent().create().inject(this)
        super.onAttachedToWindow()
        viewModel.load()
        viewModel.users.observeForever(usersObserver)
        viewModel.loading.observeForever(loadingObserver)
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        viewModel.users.removeObserver(usersObserver)
        viewModel.loading.removeObserver(loadingObserver)
    }

}
