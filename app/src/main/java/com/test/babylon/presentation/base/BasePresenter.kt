package com.test.babylon.presentation.base

open class BasePresenter<T : View> {
    var view: T? = null

    fun attachView(view: T) {
        this.view = view
    }

    open fun detachView() {
        this.view = null
    }

}