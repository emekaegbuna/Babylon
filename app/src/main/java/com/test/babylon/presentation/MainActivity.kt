package com.test.babylon.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.test.babylon.R
import com.test.babylon.presentation.posts.PostsFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction().add(
            R.id.fragmentContainer,
            PostsFragment()
        ).commit()
    }
}
