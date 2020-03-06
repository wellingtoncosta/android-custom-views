package io.github.wellingtoncosta.customviews.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import io.github.wellingtoncosta.customviews.presentation.UsersViewModel
import io.github.wellingtoncosta.customviews.presentation.main.MainActivity
import javax.inject.Singleton

@Singleton
@Component(modules = [
    ServiceModule::class
])
interface AppComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance applicationContext: Context): AppComponent
    }

    fun inject(mainActivity: MainActivity)

    fun usersViewModel(): UsersViewModel
}
