package io.github.wellingtoncosta.customviews.presentation.users

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.RelativeLayout
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import io.github.wellingtoncosta.customviews.App
import io.github.wellingtoncosta.customviews.databinding.ScreenUsersBinding
import io.github.wellingtoncosta.customviews.domain.entity.User
import io.github.wellingtoncosta.customviews.presentation.viewmodel.UsersViewModel
import javax.inject.Inject

class UsersScreen : RelativeLayout {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    private lateinit var binding: ScreenUsersBinding

    @Inject lateinit var viewModel: UsersViewModel

    private val usersAdapter = UsersAdapter()

    private val loadingObserver = Observer<Boolean> { isLoading ->
        with(binding) {
            progress.visibility = if (isLoading) View.VISIBLE else View.GONE
            usersRecyclerView.visibility = if (isLoading) View.GONE else View.VISIBLE
        }
    }

    private val usersObserver = Observer<List<User>> { users ->
        usersAdapter.dataSource = users
    }

    override fun onFinishInflate() {
        super.onFinishInflate()
        binding = ScreenUsersBinding.bind(this)
        binding.usersRecyclerView.run {
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
