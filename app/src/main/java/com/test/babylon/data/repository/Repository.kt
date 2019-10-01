package com.test.babylon.data.repository

import com.test.babylon.data.model.Comment
import com.test.babylon.data.model.Post
import com.test.babylon.data.model.User
import io.reactivex.Single

interface Repository {
    fun getPosts(): Single<List<Post>>

    fun getUserInfo(id: Int): Single<List<User>>

    fun getNumOfComments(postId: Int): Single<List<Comment>>
}