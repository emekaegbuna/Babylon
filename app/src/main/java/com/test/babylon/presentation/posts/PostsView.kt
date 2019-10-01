package com.test.babylon.presentation.posts

import com.test.babylon.data.model.Post
import com.test.babylon.presentation.base.View

interface PostsView : View {
    fun displayPosts(posts: List<Post>)
}