package io.github.wellingtoncosta.customviews.di

import dagger.Subcomponent
import io.github.wellingtoncosta.customviews.presentation.users.UsersScreen

@ViewScope
@Subcomponent
interface UsersComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): UsersComponent
    }

    fun inject(usersScreen: UsersScreen)

}
