package io.github.wellingtoncosta.customviews.presentation.users.details

import com.zhuinden.simplestack.ServiceBinder
import io.github.wellingtoncosta.customviews.R
import io.github.wellingtoncosta.customviews.presentation.navigation.ScreenKey
import io.github.wellingtoncosta.customviews.presentation.navigation.ServiceProvider
import kotlinx.android.parcel.Parcelize

@Parcelize data class UserDetailsScreenKey(
    val placeholder: String = "",
    val username: String? = null
) : ScreenKey, ServiceProvider.HasServices {

    override fun layout(): Int = R.layout.screen_user_details

    override fun bindServices(binder: ServiceBinder) { }

    override fun getScopeTag(): String = "UserDetailsScreen.Scope"

}
