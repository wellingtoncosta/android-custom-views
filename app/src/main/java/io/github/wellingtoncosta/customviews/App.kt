package io.github.wellingtoncosta.customviews

import android.app.Application
import com.github.kittinunf.fuel.core.FuelManager
import io.github.wellingtoncosta.customviews.BuildConfig.API_URL
import io.github.wellingtoncosta.customviews.api.RepositoryService
import io.github.wellingtoncosta.customviews.api.UserService
import io.github.wellingtoncosta.customviews.api.impl.RepositoryServiceFuelmpl
import io.github.wellingtoncosta.customviews.api.impl.UserServiceFuelImpl
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.dsl.module

open class App : Application() {

    private val apiModule: Module by lazy {
        module {
            single { Json(JsonConfiguration.Stable.copy(strictMode = false)) }
            single<UserService> { UserServiceFuelImpl(json = get()) }
            single<RepositoryService> { RepositoryServiceFuelmpl(json = get()) }
        }
    }

    override fun onCreate() {
        super.onCreate()

        FuelManager.instance.basePath = API_URL

        startKoin()
    }

    private fun startKoin() {
        startKoin {
            androidLogger()

            androidContext(applicationContext)

            modules(koinModules)
        }
    }

    open val koinModules: List<Module> get() = listOf(apiModule)

}
