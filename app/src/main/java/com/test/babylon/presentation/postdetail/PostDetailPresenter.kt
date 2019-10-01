package com.test.babylon.presentation.postdetail

import com.test.babylon.data.model.Post
import com.test.babylon.data.repository.Repository
import com.test.babylon.presentation.base.BasePresenter
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class PostDetailPresenter @Inject constructor(private val repository: Repository) :
    BasePresenter<PostDetailView>() {

    private val disposable = CompositeDisposable()

    fun loadData(post: Post) {
        view?.displayPost(post)
        disposable.add(repository.getUserInfo(post.userId).subscribe({
            if (it.isNotEmpty()) {
                view?.displayUserName(it.first().username)
            } else {
                view?.displayToast("No Post found for the given id")
            }
        }, {
            view?.displayToast(it.localizedMessage ?: "Error Occurred in fetching user info")
        }))
        disposable.add(repository.getNumOfComments(post.id).subscribe({
            view?.displayCommentCount(it.size)
        }, {
            view?.displayToast(it.localizedMessage ?: "Error Occurred in fetching comments")
        }))
    }

    override fun detachView() {
        disposable.clear()
        super.detachView()
    }

}