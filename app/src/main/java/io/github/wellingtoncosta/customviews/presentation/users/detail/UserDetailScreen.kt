package io.github.wellingtoncosta.customviews.presentation.users.detail

import android.content.Context
import android.util.AttributeSet
import android.widget.RelativeLayout
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.content.ContextCompat
import com.google.android.material.tabs.TabLayoutMediator
import com.zhuinden.simplestack.Backstack
import io.github.wellingtoncosta.customviews.R
import io.github.wellingtoncosta.customviews.databinding.ScreenUserDetailsBinding
import io.github.wellingtoncosta.customviews.presentation.extension.backstack

class UserDetailScreen : CoordinatorLayout {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    private lateinit var binding: ScreenUserDetailsBinding

    private val screenKey by lazy {
        Backstack.getKey<UserDetailScreenKey>(context)
    }

    private val user by lazy {
        screenKey.user
    }

    override fun onFinishInflate() {
        super.onFinishInflate()
        binding = ScreenUserDetailsBinding.bind(this)

        setupToolbar()

        setupTabs()

        binding.toolbar.title = user.userName
    }

    private fun setupToolbar() {
        with(binding.toolbar) {
            navigationIcon = ContextCompat.getDrawable(context, R.drawable.ic_arrow_back)
            setNavigationOnClickListener {
                backstack.goBack()
            }
        }
    }

    private fun setupTabs() {
        binding.viewPager.adapter = UserDetailAdapter(user)

        TabLayoutMediator(binding.tabs, binding.viewPager) { tab, position ->
            tab.text = getTabTitle(position)
        }.attach()
    }

    private fun getTabTitle(position: Int): String {
        return when (position) {
            USER_PROFILE -> "Profile"
            USER_REPOSITORIES -> "Repos"
            USER_FOLLOWERS -> "Followers"
            USER_FOLLOWING -> "Following"
            else -> throw IndexOutOfBoundsException()
        }
    }

    companion object {
        const val USER_PROFILE = 0
        const val USER_REPOSITORIES = 1
        const val USER_FOLLOWERS = 2
        const val USER_FOLLOWING = 3
    }

}
