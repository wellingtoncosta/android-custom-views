package io.github.wellingtoncosta.customviews.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import javax.inject.Singleton

@Singleton
@Component(modules = [
    ServiceModule::class,
    AppComponent.Subcomponents::class
])
interface AppComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance applicationContext: Context): AppComponent
    }

    fun usersComponent(): UsersComponent.Factory

    @Module(subcomponents = [UsersComponent::class])
    interface Subcomponents

}
