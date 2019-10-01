package com.test.babylon.presentation.posts

import com.test.babylon.data.model.Post


interface PostClickListener {
    fun onPostClicked(post: Post)
}