package io.github.wellingtoncosta.customviews

import io.github.wellingtoncosta.customviews.di.AppComponent

object Injector {
    @JvmStatic fun get(): AppComponent = App.get().getComponent()
}