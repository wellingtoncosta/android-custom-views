package io.github.wellingtoncosta.customviews.common.navigation

import android.os.Parcelable
import com.zhuinden.simplestack.navigator.DefaultViewKey
import com.zhuinden.simplestack.navigator.ViewChangeHandler
import com.zhuinden.simplestack.navigator.changehandlers.SegueViewChangeHandler

interface ScreenKey : DefaultViewKey, Parcelable {
    override fun viewChangeHandler(): ViewChangeHandler = SegueViewChangeHandler()
}