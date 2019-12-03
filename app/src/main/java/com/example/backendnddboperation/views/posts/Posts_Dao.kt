package com.example.backendnddboperation.views.posts

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.backendnddboperation.utility.AppConstant

/**
 * created by Ramanuj Kesharawani on 23/11/19
 */
@Dao
interface Posts_Dao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllPosts(stop : List<Posts>) : List<Long>

    @Query("SELECT * FROM " + AppConstant.POST_TABLE_NAME)
    fun getAllPosts():LiveData<List<Posts>>

    @Query("SELECT COUNT(*) FROM " + AppConstant.POST_TABLE_NAME)
    fun count():Long

    @Update
    fun updatePost(posts: Posts):Int

    @Query("DELETE FROM " + AppConstant.POST_TABLE_NAME + " WHERE id = :id")
    fun deletePost(id: String):Int
}