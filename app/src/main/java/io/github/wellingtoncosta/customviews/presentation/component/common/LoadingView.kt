package io.github.wellingtoncosta.customviews.presentation.component.common

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar

class LoadingView : ProgressBar {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    var isLoading: Boolean = false
        set(value) {
            hideChildrenOfRootView(value)
            showLoadingView(value)
            field = value
        }

    private fun showLoadingView(isLoading: Boolean) {
        visibility = if (isLoading) VISIBLE else GONE
    }

    private fun hideChildrenOfRootView(isLoading: Boolean) {
        getChildrenOfRoot().forEach {
            it.visibility = if (isLoading) GONE else VISIBLE
        }
    }

    private fun getChildrenOfRoot(): List<View> {
        return if (rootView is ViewGroup) {
            val viewGroup = rootView as ViewGroup
            val childCount = viewGroup.childCount
            val children = mutableListOf<View>()

            for (index in 0 until childCount) {
                val child = viewGroup.getChildAt(index)
                if (child.id != NO_ID && child.id != this.id) {
                    children.add(viewGroup.getChildAt(index))
                }
            }

            children
        } else emptyList()
    }

}
