package io.github.wellingtoncosta.customviews.presentation.users

import com.zhuinden.simplestack.ServiceBinder
import io.github.wellingtoncosta.customviews.R
import io.github.wellingtoncosta.customviews.core.navigation.ScreenKey
import io.github.wellingtoncosta.customviews.core.navigation.ServiceProvider
import io.github.wellingtoncosta.customviews.di.injector
import io.github.wellingtoncosta.customviews.presentation.viewmodel.UsersViewModel
import kotlinx.android.parcel.Parcelize

@Parcelize data class UsersScreenKey(
    val placeholder: String = ""
) : ScreenKey, ServiceProvider.HasServices {

    override fun layout(): Int = R.layout.screen_users

    /* ¯\_(ツ)_/¯ */
    override fun bindServices(binder: ServiceBinder) = binder.run {
        with(injector) {
            addService(
                UsersViewModel::class.java.simpleName,
                usersComponent().create().usersViewModel()
            )
        }
    }

    override fun getScopeTag(): String = "UsersScreen.Scope"
}
