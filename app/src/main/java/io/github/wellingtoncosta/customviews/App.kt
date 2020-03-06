package io.github.wellingtoncosta.customviews

import android.app.Application
import android.content.Context
import com.github.kittinunf.fuel.core.FuelManager
import io.github.wellingtoncosta.customviews.BuildConfig.API_URL
import io.github.wellingtoncosta.customviews.di.AppComponent
import io.github.wellingtoncosta.customviews.di.DaggerAppComponent

open class App : Application() {

    open val appComponent: AppComponent by lazy {
        DaggerAppComponent.factory().create(applicationContext)
    }

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this

        FuelManager.instance.basePath = API_URL
    }

    fun getComponent() = appComponent

    // @god forgives me
    companion object {
        private var INSTANCE: App? = null

        operator fun get(context: Context): App = context.applicationContext as App

        fun get(): App = INSTANCE!!
    }
}
