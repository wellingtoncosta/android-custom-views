package io.github.wellingtoncosta.customviews.presentation.extension

import android.view.View
import com.zhuinden.simplestack.navigator.Navigator

val View.backstack
    get() = Navigator.getBackstack(context)
