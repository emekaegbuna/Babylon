package com.test.babylon.di.component

import com.test.babylon.di.module.AppModule
import com.test.babylon.presentation.postdetail.PostDetailFragment
import com.test.babylon.presentation.posts.PostsFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {
    fun inject(postsFragment: PostsFragment)
    fun inject(postDetailFragment: PostDetailFragment)
}