package io.github.wellingtoncosta.customviews.presentation.users

import com.zhuinden.simplestack.ServiceBinder
import io.github.wellingtoncosta.customviews.Injector
import io.github.wellingtoncosta.customviews.R
import io.github.wellingtoncosta.customviews.common.navigation.ScreenKey
import io.github.wellingtoncosta.customviews.common.navigation.ServiceProvider
import kotlinx.android.parcel.Parcelize

@Parcelize data class UsersScreenKey(
    val placeholder: String = ""
) : ScreenKey, ServiceProvider.HasServices {
    override fun layout(): Int = R.layout.screen_users

    override fun bindServices(binder: ServiceBinder) {
        binder.addService(VIEW_MODEL_TAG, Injector.get().usersViewModel())
    }

    override fun getScopeTag(): String = "UsersScreen.Scope"

    companion object {
        const val VIEW_MODEL_TAG = "UsersScreen.ViewModel"
    }
}