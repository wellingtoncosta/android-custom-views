package io.github.wellingtoncosta.customviews.presentation.navigation

import com.zhuinden.simplestack.ScopeKey
import com.zhuinden.simplestack.ScopedServices
import com.zhuinden.simplestack.ServiceBinder

class ServiceProvider : ScopedServices {
    interface HasServices : ScopeKey {
        fun bindServices(binder: ServiceBinder)
    }

    override fun bindServices(serviceBinder: ServiceBinder) {
        val screenKey = serviceBinder.getKey<ScreenKey>()
        if (screenKey is HasServices) {
            screenKey.bindServices(serviceBinder)
        }
    }
}