package io.github.wellingtoncosta.customviews.core.navigation

import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import com.zhuinden.simplestack.StateChange
import com.zhuinden.simplestack.StateChanger
import com.zhuinden.simplestack.navigator.Navigator
import com.zhuinden.simplestack.navigator.changehandlers.FadeViewChangeHandler
import io.github.wellingtoncosta.customviews.R
import kotlinx.coroutines.*

const val VIEW_COROUTINE_SCOPE_KEY = R.id.view_coroutine_scope

class ViewStateChanger(
    private val activity: Activity,
    private val root: ViewGroup
) : StateChanger {
    override fun handleStateChange(stateChange: StateChange, completionCallback: StateChanger.Callback) {
        val newKey = stateChange.topNewKey<ScreenKey>()
        val previousKey = stateChange.topPreviousKey<ScreenKey?>()

        val newView = LayoutInflater.from(stateChange.createContext(activity, newKey))
            .inflate(newKey.layout(), root, false)

        val viewScope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)
        newView.setTag(VIEW_COROUTINE_SCOPE_KEY, viewScope)

        val previousView = root.getChildAt(0)
        Navigator.persistViewToState(previousView)
        Navigator.restoreViewFromState(newView)

        if (previousKey == null || previousView == null) {
            root.addView(newView)
            completionCallback.stateChangeComplete()
            return
        }

        val previousViewScope = previousView.getTag(VIEW_COROUTINE_SCOPE_KEY) as CoroutineScope?
        previousViewScope?.cancel()

        val viewChangeHandler = when (stateChange.direction) {
            StateChange.FORWARD -> newKey.viewChangeHandler()
            StateChange.BACKWARD -> previousKey.viewChangeHandler()
            else -> FadeViewChangeHandler()
        }

        viewChangeHandler.performViewChange(root, previousView, newView, stateChange.direction) {
            completionCallback.stateChangeComplete()
        }
    }
}