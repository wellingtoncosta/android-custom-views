package io.github.wellingtoncosta.customviews.common.navigation

import com.zhuinden.simplestack.ScopedServices
import com.zhuinden.simplestack.ServiceBinder

class ServiceProvider : ScopedServices {
    interface HasServices {
        fun bindServices(binder: ServiceBinder)
    }

    override fun bindServices(serviceBinder: ServiceBinder) {
        val screenKey = serviceBinder.getKey<ScreenKey>()
        if (screenKey is HasServices) {
            screenKey.bindServices(serviceBinder)
        }
    }
}