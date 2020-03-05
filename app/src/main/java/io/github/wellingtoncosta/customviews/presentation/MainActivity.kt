package io.github.wellingtoncosta.customviews.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import io.github.wellingtoncosta.customviews.App
import io.github.wellingtoncosta.customviews.R
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject lateinit var usersViewModel: UsersViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as App).appComponent.inject(this)

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        loadUsers()
    }

    private fun loadUsers() {
        usersViewModel.loading.observe(this, Observer {
            Log.d("loading - users", it.toString())
        })

        usersViewModel.users.observe(this, Observer {
            Log.d("users", it.toString())
        })

        usersViewModel.error.observe(this, Observer {
            Log.d("error - users", it.toString())
        })

        usersViewModel.load()
    }

}
