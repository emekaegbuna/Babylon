package com.test.babylon

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockitokotlin2.never
import com.test.babylon.data.model.Post
import com.test.babylon.data.repository.Repository
import com.test.babylon.presentation.posts.PostsPresenter
import com.test.babylon.presentation.posts.PostsView
import io.reactivex.Single
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class PostsPresenterTest {

    @Mock
    lateinit var view: PostsView

    @Rule
    @JvmField
    var rule: TestRule = InstantTaskExecutorRule()

    @Mock
    lateinit var repository: Repository

    private lateinit var postsPresenter: PostsPresenter


    @Before
    fun setUp() {
        this.postsPresenter = PostsPresenter(repository)
        postsPresenter.attachView(view)
    }

    @Test
    fun getPostReturnsData() {
        val posts = mutableListOf(
            Post(
                1,
                1,
                "sunt aut facere repellat provident occaecati excepturi optio reprehenderit",
                "Test"
            )
        )
        `when`(repository.getPosts()).thenReturn(Single.just(posts))
        postsPresenter.getPosts()

        verify(view).displayPosts(posts)
    }


    @Test
    fun getPostReturnsError() {
        val errorMessage = "Exception"
        `when`(repository.getPosts()).thenReturn(Single.error(RuntimeException(errorMessage)))
        postsPresenter.getPosts()
        verify(view).displayToast(errorMessage)
    }

    @After
    fun tearDown() {
        postsPresenter.detachView()
    }

}