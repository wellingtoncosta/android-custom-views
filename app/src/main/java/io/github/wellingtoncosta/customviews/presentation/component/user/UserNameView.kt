package io.github.wellingtoncosta.customviews.presentation.component.user

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import io.github.wellingtoncosta.customviews.domain.entity.User

class UserNameView : AppCompatTextView {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    fun bind(user: User) = user.run {
        text = userName
    }
}