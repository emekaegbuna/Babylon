package com.test.babylon.presentation.postdetail

import com.test.babylon.data.model.Post
import com.test.babylon.presentation.base.View

interface PostDetailView : View {

    fun displayPost(post: Post)
    fun displayUserName(username: String)
    fun displayCommentCount(count: Int)
}