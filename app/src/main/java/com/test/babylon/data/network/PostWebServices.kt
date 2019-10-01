package com.test.babylon.data.network

import com.test.babylon.data.model.Comment
import com.test.babylon.data.model.Post
import com.test.babylon.data.model.User
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface PostWebServices {

    @GET("posts")
    fun getPosts(): Single<List<Post>>

    @GET("users")
    fun getUserInfo(@Query("id") id: Int): Single<List<User>>

    @GET("comments")
    fun getNumOfComments(@Query("postId") postId: Int): Single<List<Comment>>

}