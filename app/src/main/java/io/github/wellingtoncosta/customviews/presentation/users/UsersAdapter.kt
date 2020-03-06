package io.github.wellingtoncosta.customviews.presentation.users

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import io.github.wellingtoncosta.customviews.R
import io.github.wellingtoncosta.customviews.databinding.ViewItemRowUserBinding
import io.github.wellingtoncosta.customviews.domain.entity.User
import kotlinx.android.extensions.LayoutContainer

class UsersAdapter : RecyclerView.Adapter<UsersAdapter.ViewHolder>() {
    var dataSource: List<User> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.view_item_row_user, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int = dataSource.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(dataSource[position])
    }

    inner class ViewHolder (override val containerView: View): RecyclerView.ViewHolder(containerView), LayoutContainer {
        private val binding = ViewItemRowUserBinding.bind(containerView)

        fun bind(user: User) = containerView.run {
            binding.imageUserAvatar.bind(user)
            binding.textUserName.bind(user)
            binding.textUserDescription.bind(user)
        }
    }
}
