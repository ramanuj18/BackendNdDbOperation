package com.example.backendnddboperation.views.posts

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.backendnddboperation.R
import kotlinx.android.synthetic.main.activity_post_detail_acitivity.*

class PostDetailActivity : AppCompatActivity() {
         var post:Posts?=null
    lateinit var postController:PostsController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post_detail_acitivity)
        postController= PostsController()
        post=intent.getSerializableExtra("postDetail") as Posts

        btn_update.setOnClickListener {
            post?.let {
                it.title=edt_title.text.toString()
                it.body=edt_body.text.toString()
                postController.callUpdatePost(it)
                finish()
            }

        }
        setDataToView()
    }

    private fun setDataToView() {
        post?.let {
            edt_title.setText("${it.title}")
            edt_body.setText("${it.body}")
        }
    }
}
