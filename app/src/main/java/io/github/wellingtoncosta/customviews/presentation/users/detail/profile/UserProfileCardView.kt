package io.github.wellingtoncosta.customviews.presentation.users.detail.profile

import android.content.Context
import android.util.AttributeSet
import androidx.cardview.widget.CardView
import io.github.wellingtoncosta.customviews.databinding.ComponentUserDetailBinding
import io.github.wellingtoncosta.customviews.domain.entity.User
import io.github.wellingtoncosta.customviews.presentation.extension.inflater

class UserProfileCardView(
    context: Context,
    attrs: AttributeSet
) : CardView(context, attrs) {
    private var _bindingUserDetailComponents: ComponentUserDetailBinding? = null
    private val bindingUserDetailComponents get() = _bindingUserDetailComponents!!

    init {
        _bindingUserDetailComponents = ComponentUserDetailBinding.inflate(inflater, this, true)
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        _bindingUserDetailComponents = null
    }

    fun bind(user: User) = user.run {
        bindingUserDetailComponents.textUserName.bind(this)
        bindingUserDetailComponents.textUserDescription.bind(this)
    }
}