package io.github.wellingtoncosta.customviews.presentation.users.details

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import io.github.wellingtoncosta.customviews.R

class UserDetailsViewPager : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun getItemViewType(position: Int) = position % 2 * 2

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
        val view = LayoutInflater.from(parent.context).inflate(R.layout.user_profile_mock, parent, false)
        return UserProfileViewHolder(view)
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
        Log.d("bind-user-details", "position: $position")
    }

    inner class UserProfileViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

    inner class UserReposViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

    inner class UserFollowersViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

    inner class UserFollowingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

    companion object {
        const val USER_PROFILE_INDEX = 0
        const val USER_REPOS_INDEX = 2
        const val USER_FOLLOWERS_INDEX = 4
        const val USER_FOLLOWING_INDEX = 6
    }

}