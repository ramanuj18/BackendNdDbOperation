package com.example.backendnddboperation.views.posts

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.example.backendnddboperation.R
import com.example.backendnddboperation.roomdb.AppDatabase
import kotlinx.android.synthetic.main.activity_employee_list.*

class PostListActivity : AppCompatActivity(), View.OnClickListener,PostsRecyclerViewAdapter.OnRecyclerItemClick {

    var postLiveData: LiveData<List<Posts>>? = null
    lateinit var postsDao: Posts_Dao
    lateinit var postsController: PostsController
    var postList= listOf<Posts>()
    lateinit var adapter:PostsRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_employee_list)
        postsController = PostsController()
        postsDao = AppDatabase.invoke().postDao()
        adapter= PostsRecyclerViewAdapter(postList,this)
        recyclerview_posts.adapter=adapter

        if(postsDao.count()==0L){
            postsController.callGetPostListApi()
        }

        registerPostsObserver()
    }

    override fun onClick(view: View?) {

    }

    private fun registerPostsObserver() {
        postLiveData = postsDao.getAllPosts()
        postLiveData?.observe(this, object : Observer<List<Posts>> {
            override fun onChanged(t: List<Posts>?) {
               t?.let {
                   adapter.setPostList(t)
               }
            }

        })
    }

    override fun onItemClick(posts: Posts, position: Int) {
        val intent=Intent(this,PostDetailActivity::class.java)
        intent.putExtra("postDetail",posts)
        startActivity(intent)
    }

    override fun onItemLongClick(id: String) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Delete Post!")
        builder.setMessage("Are you sure do you want to delete this post?")
        builder.setIcon(android.R.drawable.ic_dialog_alert)

        builder.setPositiveButton("Yes"){dialogInterface, which ->
           postsController.callDeletePost(id)
        }

        builder.setNegativeButton("No"){dialogInterface, which ->
            dialogInterface.dismiss()
        }
        val alertDialog: AlertDialog = builder.create()
        alertDialog.setCancelable(false)
        alertDialog.show()

    }
}
