package io.github.wellingtoncosta.customviews.presentation.users.details

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.tabs.TabLayoutMediator
import com.zhuinden.simplestack.Backstack
import io.github.wellingtoncosta.customviews.R
import io.github.wellingtoncosta.customviews.databinding.ScreenUserDetailsBinding
import io.github.wellingtoncosta.customviews.presentation.extension.backstack

class UserDetailsScreen : RelativeLayout {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    private lateinit var binding: ScreenUserDetailsBinding

    override fun onFinishInflate() {
        super.onFinishInflate()

        binding = ScreenUserDetailsBinding.bind(this)

        setupToolbar()

        setupTabs()

        Backstack.getKey<UserDetailsScreenKey>(context).username
            ?.let { binding.toolbar.title = it }
            ?: throw IllegalArgumentException( "username cannot be null")
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
        binding.viewPager.adapter = UserDetailsViewPager()

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
