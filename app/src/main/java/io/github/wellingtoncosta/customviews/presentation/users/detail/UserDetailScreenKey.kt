package io.github.wellingtoncosta.customviews.presentation.users.detail

import io.github.wellingtoncosta.customviews.R
import io.github.wellingtoncosta.customviews.domain.entity.User
import io.github.wellingtoncosta.customviews.presentation.navigation.ScreenKey
import kotlinx.android.parcel.Parcelize

@Parcelize data class UserDetailScreenKey(
    val user: User
) : ScreenKey {
    override fun layout(): Int = R.layout.screen_user_details
}
