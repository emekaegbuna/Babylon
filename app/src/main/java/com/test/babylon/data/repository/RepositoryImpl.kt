package com.test.babylon.data.repository

import com.test.babylon.data.network.PostWebServices
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class RepositoryImpl @Inject constructor(private val postWebServices: PostWebServices) :
    Repository {

    override fun getPosts() =
        postWebServices.getPosts().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())

    override fun getUserInfo(id: Int) =
        postWebServices.getUserInfo(id).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())


    override fun getNumOfComments(postId: Int) =
        postWebServices.getNumOfComments(postId).subscribeOn(Schedulers.io()).observeOn(
            AndroidSchedulers.mainThread()
        )

}