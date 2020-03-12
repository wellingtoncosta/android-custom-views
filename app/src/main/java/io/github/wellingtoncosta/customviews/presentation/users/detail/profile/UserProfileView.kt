package io.github.wellingtoncosta.customviews.presentation.users.detail.profile

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import io.github.wellingtoncosta.customviews.databinding.PageUserProfileBinding
import io.github.wellingtoncosta.customviews.domain.entity.User

class UserProfileView(
    context: Context,
    attrs: AttributeSet
) : LinearLayout(context, attrs) {
    private var _binding: PageUserProfileBinding? = null
    private val binding get() = _binding!!

    override fun onFinishInflate() {
        super.onFinishInflate()
        _binding = PageUserProfileBinding.bind(this)
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        _binding = null
    }

    fun bind(user: User) = user.run {
        binding.cardUserProfile.bind(user)
    }
}
