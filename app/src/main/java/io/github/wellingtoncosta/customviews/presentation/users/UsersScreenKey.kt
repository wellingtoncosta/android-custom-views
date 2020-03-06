package io.github.wellingtoncosta.customviews.presentation.users

import com.zhuinden.simplestack.ServiceBinder
import io.github.wellingtoncosta.customviews.R
import io.github.wellingtoncosta.customviews.presentation.navigation.ScreenKey
import io.github.wellingtoncosta.customviews.presentation.navigation.ServiceProvider
import kotlinx.android.parcel.Parcelize

@Parcelize data class UsersScreenKey constructor(
    val placeholder: String = ""
) : ScreenKey, ServiceProvider.HasServices {

    /*@IgnoredOnParcel
    lateinit var onBindViewModel: () -> UsersViewModel*/

    override fun layout(): Int = R.layout.screen_users

    override fun bindServices(binder: ServiceBinder) {
        /*if (this::onBindViewModel.isInitialized) {
            binder.addService(VIEW_MODEL_TAG, onBindViewModel.invoke())
        }*/
    }

    override fun getScopeTag(): String = "UsersScreen.Scope"

    /*companion object {
        const val VIEW_MODEL_TAG = "UsersScreen.ViewModel"
    }*/
}
