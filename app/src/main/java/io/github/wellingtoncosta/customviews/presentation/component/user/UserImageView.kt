package io.github.wellingtoncosta.customviews.presentation.component.user

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView
import com.bumptech.glide.Glide
import io.github.wellingtoncosta.customviews.config.GlideApp
import io.github.wellingtoncosta.customviews.domain.entity.User

class UserImageView : AppCompatImageView {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    fun bind(user: User) = user.run {
        GlideApp
            .with(context)
            .load(user.avatarUrl)
            .into(this@UserImageView)
    }
}