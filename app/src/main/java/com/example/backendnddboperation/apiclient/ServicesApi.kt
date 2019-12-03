package com.example.backendnddboperation.apiclient

import com.example.backendnddboperation.views.posts.Posts
import com.google.gson.JsonObject
import io.reactivex.Observable
import retrofit2.http.*
import kotlin.collections.HashMap

/**
 * created by Ramanuj Kesharawani on 23/11/19
 */
interface ServicesApi {
    @POST
    fun postApiCall(@Url url:String,@Body requestBody:JsonObject):Observable<JsonObject>

    @GET
    fun getApiCall(@Url url:String):Observable<JsonObject>

    @GET
    fun getApiCallWithParam(@Url url:String,@QueryMap(encoded=true) paramMap:HashMap<String,String>):Observable<JsonObject>

    @GET("employee/{id}")
    fun getEmployeeDetail(@Path("id") id:String):Observable<JsonObject>

    @GET
    fun getAllPosts(@Url url:String):Observable<List<Posts>>

    @PUT("posts/{id}")
    fun updatePost(@Path("id") id:String,@Body requestBody: JsonObject):Observable<Posts>

    @DELETE("posts/{id}")
    fun deletePost(@Path("id") id:String):Observable<Posts>
}