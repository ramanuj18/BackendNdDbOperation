package com.example.backendnddboperation.views.posts

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.backendnddboperation.utility.AppConstant
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable


/**
 * created by Ramanuj Kesharawani on 23/11/19
 */
@Entity(tableName = AppConstant.POST_TABLE_NAME)
class Posts :Serializable{
    @SerializedName("userId")
    @ColumnInfo(name="userId")
    var userId: Int? = null
    @PrimaryKey
    @SerializedName("id")
    @ColumnInfo(name="id")
    var id: Int? = null
    @SerializedName("title")
    @ColumnInfo(name="title")
    var title: String? = null
    @SerializedName("body")
    @ColumnInfo(name="body")
    var body: String? = null
}