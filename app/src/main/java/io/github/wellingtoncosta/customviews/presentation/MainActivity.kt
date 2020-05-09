package io.github.wellingtoncosta.customviews.presentation

import android.content.Context
import android.os.Bundle
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import com.zhuinden.simplestack.History
import com.zhuinden.simplestack.navigator.Navigator
import io.github.wellingtoncosta.customviews.core.navigation.ServiceProvider
import io.github.wellingtoncosta.customviews.core.navigation.ViewStateChanger
import io.github.wellingtoncosta.customviews.presentation.users.UsersScreenKey

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val mainContainer = MainContainer(this)
        setContentView(mainContainer)

        val stateChanger = ViewStateChanger(this, mainContainer)

        Navigator
            .configure()
            .setStateChanger(stateChanger)
            .setScopedServices(ServiceProvider())
            .install(this, mainContainer, History.of(UsersScreenKey()))
    }

    override fun onBackPressed() {
        if (!Navigator.onBackPressed(this)) {
            super.onBackPressed()
        }
    }

    inner class MainContainer(context: Context) : FrameLayout(context) {
        init {
            layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT)
        }
    }
}
