package io.github.wellingtoncosta.customviews.presentation.users.detail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import io.github.wellingtoncosta.customviews.R
import io.github.wellingtoncosta.customviews.databinding.PageUserProfileBinding
import io.github.wellingtoncosta.customviews.domain.entity.User
import io.github.wellingtoncosta.customviews.presentation.extension.inflater
import io.github.wellingtoncosta.customviews.presentation.users.detail.profile.UserProfileView

class UserDetailAdapter(private val user: User) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun getItemViewType(position: Int) = position

    override fun getItemCount() = 4

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            USER_PROFILE_INDEX -> inflateUserProfileViewHolder(parent)
            USER_REPOS_INDEX -> inflateUserReposViewHolder(parent)
            USER_FOLLOWERS_INDEX -> inflateUserFollowersViewHolder(parent)
            USER_FOLLOWING_INDEX -> inflateUserFollowingViewHolder(parent)
            else -> throw IllegalArgumentException("viewType $viewType is not valid.")
        }
    }

    private fun inflateUserProfileViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        val inflater = parent.inflater
        val binding = PageUserProfileBinding.inflate(inflater, parent, false)
        return UserProfileViewHolder(binding)
    }

    private fun inflateUserReposViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.user_profile_mock, parent, false)
        return UserReposViewHolder(view)
    }

    private fun inflateUserFollowersViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.user_profile_mock, parent, false)
        return UserFollowersViewHolder(view)
    }

    private fun inflateUserFollowingViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.user_profile_mock, parent, false)
        return UserFollowingViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is UserDetailPage) {
            holder.bind(user)
        }
    }

    inner class UserProfileViewHolder(
        private val binding: PageUserProfileBinding
    ) : RecyclerView.ViewHolder(binding.root), UserDetailPage {
        override fun bind(user: User) = user.run {
            (binding.root as UserProfileView).bind(user)
        }
    }

    inner class UserReposViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    inner class UserFollowersViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    inner class UserFollowingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    internal interface UserDetailPage {
        fun bind(user: User)
    }

    companion object {
        const val USER_PROFILE_INDEX = 0
        const val USER_REPOS_INDEX = 1
        const val USER_FOLLOWERS_INDEX = 2
        const val USER_FOLLOWING_INDEX = 3
    }

}