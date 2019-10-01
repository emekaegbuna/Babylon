package com.test.babylon.presentation.posts

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.test.babylon.data.model.Post
import kotlinx.android.synthetic.main.view_holder_post.view.*


class PostViewHolder(view: View): RecyclerView.ViewHolder(view){
    fun bind(post: Post, listener: PostClickListener){
        itemView.tv_title.text = post.title
        itemView.setOnClickListener {
            listener.onPostClicked(post)
        }
    }
}