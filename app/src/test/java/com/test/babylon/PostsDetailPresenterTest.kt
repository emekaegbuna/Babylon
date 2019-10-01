package com.test.babylon

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.test.babylon.data.model.Comment
import com.test.babylon.data.model.Post
import com.test.babylon.data.model.User
import com.test.babylon.data.repository.Repository
import com.test.babylon.presentation.postdetail.PostDetailPresenter
import com.test.babylon.presentation.postdetail.PostDetailView
import io.reactivex.Single
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class PostsDetailPresenterTest {

    @Mock
    lateinit var view: PostDetailView

    @Rule
    @JvmField
    var rule: TestRule = InstantTaskExecutorRule()

    @Mock
    lateinit var repository: Repository

    private lateinit var postsPresenter: PostDetailPresenter

    private val post = Post(1, 1, "demo", "demo")
    private val users = mutableListOf(
        User(
            1,
            "Demo",
            "demo"
        )
    )
    private val comments = mutableListOf(
        Comment(
            1, 1, "", "", ""
        )
    )

    @Before
    fun setUp() {
        this.postsPresenter = PostDetailPresenter(repository)
        postsPresenter.attachView(view)
    }

    @Test
    fun getUserData() {

        `when`(repository.getUserInfo(1)).thenReturn(Single.just(users))
        `when`(repository.getNumOfComments(1)).thenReturn(Single.just(comments))
        postsPresenter.loadData(post)

        verify(view).displayPost(post)
        verify(view).displayUserName(users.first().username)
        verify(view).displayCommentCount(1)
    }


    @Test
    fun getPostReturnsError() {
        val errorMessage = "Exception"
        `when`(repository.getUserInfo(1)).thenReturn(Single.error(RuntimeException(errorMessage)))
        `when`(repository.getNumOfComments(1)).thenReturn(Single.just(comments))
        postsPresenter.loadData(post)

        verify(view).displayPost(post)
        verify(view).displayToast(errorMessage)
        verify(view).displayCommentCount(1)
    }


    @Test
    fun getCommentsReturnsError() {
        val errorMessage = "Exception"
        `when`(repository.getNumOfComments(1)).thenReturn(Single.error(RuntimeException(errorMessage)))
        `when`(repository.getUserInfo(1)).thenReturn(Single.just(users))
        postsPresenter.loadData(post)

        verify(view).displayPost(post)
        verify(view).displayToast(errorMessage)
        verify(view).displayUserName(users.first().username)
    }

    @After
    fun tearDown() {
        postsPresenter.detachView()
    }

}