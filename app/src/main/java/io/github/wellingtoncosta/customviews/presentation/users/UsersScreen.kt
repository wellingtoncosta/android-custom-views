package io.github.wellingtoncosta.customviews.presentation.users

import android.content.Context
import android.util.AttributeSet
import android.widget.RelativeLayout
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.zhuinden.simplestack.navigator.Navigator
import io.github.wellingtoncosta.customviews.domain.entity.User
import io.github.wellingtoncosta.customviews.presentation.UsersViewModel
import kotlinx.android.synthetic.main.screen_users.view.*

class UsersScreen : RelativeLayout {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    private val usersAdapter = UsersAdapter()

    private val viewModel by lazy {
        Navigator.lookupService<UsersViewModel>(context, UsersScreenKey.VIEW_MODEL_TAG)
    }

    private val usersObserver = Observer<List<User>> { users ->
        usersAdapter.dataSource = users
    }

    override fun onFinishInflate() {
        super.onFinishInflate()
        users.run {
            adapter = usersAdapter
            addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))
        }
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        viewModel.load()
        viewModel.users.observeForever(usersObserver)
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        viewModel.users.removeObserver(usersObserver)
    }
}