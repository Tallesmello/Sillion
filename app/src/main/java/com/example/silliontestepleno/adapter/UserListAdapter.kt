package com.example.silliontestepleno.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.silliontestepleno.R
import com.example.silliontestepleno.data.User

class UserListAdapter(context: Context, private val userList: List<User>) :
    ArrayAdapter<User>(context, R.layout.list_item_user, userList) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val itemView = convertView ?: LayoutInflater.from(context)
            .inflate(R.layout.list_item_user, parent, false)

        val avatarImageView = itemView.findViewById<ImageView>(R.id.avatarImageView)
        val userNameTextView = itemView.findViewById<TextView>(R.id.userNameTextView)

        val user = userList[position]
        userNameTextView.text = "${user.first_name} ${user.last_name}"

        if (!user.avatar.isNullOrEmpty()) {
            loadImageWithGlide(user.avatar, avatarImageView)
        }

        return itemView
    }

    private fun loadImageWithGlide(imageUrl: String, imageView: ImageView) {
        Glide.with(context)
            .load(imageUrl)
            .placeholder(R.drawable.ic_launcher_background)
            .error(R.drawable.ic_launcher_background)
            .into(imageView)
    }
}