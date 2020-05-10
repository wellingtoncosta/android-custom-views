package io.github.wellingtoncosta.customviews.di

import com.zhuinden.simplestack.ServiceBinder
import io.github.wellingtoncosta.customviews.di.AppComponent.Companion.TAG_INJECTOR

val ServiceBinder.injector: AppComponent get() = lookupService(TAG_INJECTOR)