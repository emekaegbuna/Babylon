package com.test.babylon.presentation.posts

import com.test.babylon.data.repository.Repository
import com.test.babylon.presentation.base.BasePresenter
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class PostsPresenter @Inject constructor(private val repository: Repository) :
    BasePresenter<PostsView>() {

    private val compositeDisposable = CompositeDisposable()
    fun getPosts() {
        compositeDisposable.add(
            repository.getPosts()
                .subscribe({ posts ->
                    view?.displayPosts(posts)
                }, {
                    view?.displayToast(it.localizedMessage ?: "Error Occurred")
                })
        )
    }

    override fun detachView() {
        compositeDisposable.clear()
        super.detachView()
    }

}