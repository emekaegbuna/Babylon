package com.test.babylon.presentation.postdetail


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.test.babylon.R
import com.test.babylon.data.model.Post
import com.test.babylon.data.repository.Repository
import com.test.babylon.di.component.DaggerAppComponent
import com.test.babylon.di.module.AppModule
import com.test.babylon.presentation.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_post_detail.*
import javax.inject.Inject


class PostDetailFragment : BaseFragment(), PostDetailView {
    override fun displayPost(post: Post) {
        tv_postTitle.text = post.title
        tv_postBody.text = post.body
    }

    override fun displayUserName(username: String) {
        tv_authorName.text = "Author Name:$username"
    }

    override fun displayCommentCount(count: Int) {
        tv_comments.text = "Number of Comments:$count"
    }


    private lateinit var presenter: PostDetailPresenter

    @Inject
    lateinit var repository: Repository

    lateinit var post: Post

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_post_detail, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        DaggerAppComponent.builder()
            .appModule(AppModule())
            .build()
            .inject(this)

        post = arguments?.getParcelable("post")!!

        presenter = PostDetailPresenter(repository)
        presenter.attachView(this)

        presenter.loadData(post)
    }

    override fun onDestroyView() {
        presenter.detachView()
        super.onDestroyView()
    }


}
