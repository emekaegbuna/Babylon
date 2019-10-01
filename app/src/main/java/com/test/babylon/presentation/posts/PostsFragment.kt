package com.test.babylon.presentation.posts


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.test.babylon.R
import com.test.babylon.data.model.Post
import com.test.babylon.data.repository.Repository
import com.test.babylon.di.component.DaggerAppComponent
import com.test.babylon.di.module.AppModule
import com.test.babylon.presentation.base.BaseFragment
import com.test.babylon.presentation.postdetail.PostDetailFragment
import kotlinx.android.synthetic.main.fragment_post.*
import javax.inject.Inject


class PostsFragment : BaseFragment(), PostsView {


    private lateinit var presenter: PostsPresenter


    private lateinit var postAdapter: PostAdapter

    @Inject
    lateinit var repository: Repository

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_post, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        DaggerAppComponent.builder()
            .appModule(AppModule())
            .build()
            .inject(this)
        recyclerView.layoutManager = LinearLayoutManager(context)
        postAdapter =
            PostAdapter(object :
                PostClickListener {
                override fun onPostClicked(post: Post) {
                    val fragmentManager = activity?.supportFragmentManager
                    val transaction = fragmentManager?.beginTransaction()
                    val args = Bundle()
                    args.putParcelable("post", post)
                    val userPostFragment =
                        PostDetailFragment()
                    userPostFragment.arguments = args
                    transaction?.replace(R.id.fragmentContainer, userPostFragment)
                        ?.addToBackStack(null)
                        ?.commit()
                }
            })
        recyclerView.adapter = postAdapter
        presenter = PostsPresenter(repository)
        presenter.attachView(this)
        presenter.getPosts()
    }

    override fun displayPosts(posts: List<Post>) {
        postAdapter.updateData(posts)
    }

    override fun onDestroyView() {
        presenter.detachView()
        super.onDestroyView()
    }

}
