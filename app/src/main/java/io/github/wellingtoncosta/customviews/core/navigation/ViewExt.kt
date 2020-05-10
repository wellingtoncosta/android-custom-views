package io.github.wellingtoncosta.customviews.core.navigation

import android.view.View
import android.view.ViewGroup
import kotlinx.coroutines.CoroutineScope

val View.viewScope get() = findTopMostParentWithScope()

private fun View.findTopMostParentWithScope(): CoroutineScope {
    val scope = getTag(VIEW_COROUTINE_SCOPE_KEY) as CoroutineScope?
    return scope ?: checkNotNull(parent as ViewGroup) {
        "CoroutineScope is not set for this view-tree hierarchy."
    }.findTopMostParentWithScope()
}
