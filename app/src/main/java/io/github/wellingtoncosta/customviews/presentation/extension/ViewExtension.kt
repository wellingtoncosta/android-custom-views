package io.github.wellingtoncosta.customviews.presentation.extension

import android.view.LayoutInflater
import android.view.View
import androidx.lifecycle.ViewModel
import com.zhuinden.simplestack.navigator.Navigator

val View.backstack
    get() = Navigator.getBackstack(context)

val View.inflater
    get() = LayoutInflater.from(context)

inline fun <reified T : ViewModel> View.viewModel(
    tag: String = T::class.java.simpleName
) = lazy(LazyThreadSafetyMode.NONE) {
    backstack.lookupService(tag) as T
}
