package io.github.wellingtoncosta.customviews.presentation.users

import com.zhuinden.simplestack.ServiceBinder
import io.github.wellingtoncosta.customviews.R
import io.github.wellingtoncosta.customviews.presentation.navigation.ScreenKey
import io.github.wellingtoncosta.customviews.presentation.navigation.ServiceProvider
import kotlinx.android.parcel.Parcelize

@Parcelize data class UsersScreenKey constructor(
    val placeholder: String = ""
) : ScreenKey, ServiceProvider.HasServices {

    override fun layout(): Int = R.layout.screen_users

    override fun bindServices(binder: ServiceBinder) { }

    override fun getScopeTag(): String = "UsersScreen.Scope"

}
