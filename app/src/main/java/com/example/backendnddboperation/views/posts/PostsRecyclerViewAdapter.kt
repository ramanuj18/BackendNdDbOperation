package com.example.backendnddboperation.views.posts

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.backendnddboperation.R
import org.w3c.dom.Text

/**
 * created by Ramanuj Kesharawani on 23/11/19
 */
class PostsRecyclerViewAdapter(private var postList: List<Posts>,private var onRecyclerItemClick: OnRecyclerItemClick) :RecyclerView.Adapter<PostsRecyclerViewAdapter.ViewHolder>(){
    lateinit var context:Context
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context=parent.context
        val view=LayoutInflater.from(context).inflate(R.layout.posts_item_layout,null)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return postList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val post=postList[position]
        holder.textUserId.text="${post.userId}"
        holder.textTitle.text=post.title
        holder.textBody.text=post.body

        holder.itemView.setOnClickListener {
            onRecyclerItemClick.onItemClick(post,position)
        }

        holder.itemView.setOnLongClickListener {
            onRecyclerItemClick.onItemLongClick(post.id.toString())
            true
        }
    }

    fun setPostList(list: List<Posts>){
        this.postList=list
        notifyDataSetChanged()
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
         var textUserId:TextView = view.findViewById(R.id.text_user_id)
        var textTitle:TextView = view.findViewById(R.id.text_title)
        var textBody:TextView = view.findViewById(R.id.text_body)
    }

    interface OnRecyclerItemClick{
        fun onItemClick(posts: Posts,position:Int)
        fun onItemLongClick(id:String)
    }
}