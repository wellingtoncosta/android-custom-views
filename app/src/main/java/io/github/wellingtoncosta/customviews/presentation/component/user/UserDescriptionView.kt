package io.github.wellingtoncosta.customviews.presentation.component.user

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import io.github.wellingtoncosta.customviews.R
import io.github.wellingtoncosta.customviews.domain.entity.User

class UserDescriptionView : AppCompatTextView {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    fun bind(user: User) = user.run {
        text = resources.getString(
            R.string.text_user_description,
            repositories,
            followers,
            following
        )
    }
}