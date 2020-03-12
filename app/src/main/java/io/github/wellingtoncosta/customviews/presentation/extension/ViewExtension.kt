package io.github.wellingtoncosta.customviews.presentation.extension

import android.view.LayoutInflater
import android.view.View
import com.zhuinden.simplestack.navigator.Navigator

val View.backstack
    get() = Navigator.getBackstack(context)

val View.inflater
    get() = LayoutInflater.from(context)