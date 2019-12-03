package com.example.backendnddboperation.views.posts

import android.os.AsyncTask
import android.util.Log
import com.example.backendnddboperation.R
import com.example.backendnddboperation.apiclient.ApiClient
import com.example.backendnddboperation.roomdb.AppDatabase
import com.example.backendnddboperation.utility.AppConstant
import com.example.backendnddboperation.utility.AppUtility
import com.example.backendnddboperation.utility.ThisApplication
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

/**
 * created by Ramanuj Kesharawani on 23/11/19
 */
class PostsController {

     fun callGetPostListApi(){
        if(AppUtility.isInternetConnected()) {
            ApiClient.getServices().getAllPosts(AppConstant.getAllPosts)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<List<Posts>> {
                    override fun onComplete() {

                    }

                    override fun onSubscribe(d: Disposable) {
                        Log.d("TAG", "onSubscribe")
                    }

                    override fun onNext(t: List<Posts>) {
                        Log.d("TAG", "onNext")
                        insertAllRecordsToDb(t)
                    }

                    override fun onError(e: Throwable) {
                        Log.d("TAG", "onError")
                    }

                })
        }else{
            ThisApplication.showToast(ThisApplication.getAppInstance().getString(R.string.check_internet_connection))
        }
    }

    fun callUpdatePost(posts: Posts){
        if(AppUtility.isInternetConnected()){
            ApiClient.getServices().updatePost(posts.id.toString(),AppUtility.getJsonObject(posts))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object :Observer<Posts>{
                    override fun onComplete() {

                    }

                    override fun onSubscribe(d: Disposable) {

                    }

                    override fun onNext(t: Posts) {
                        updatePost(t)
                    }

                    override fun onError(e: Throwable) {

                    }

                })
        }else{
            ThisApplication.showToast(ThisApplication.getAppInstance().getString(R.string.check_internet_connection))
        }
    }

    fun callDeletePost(id:String){
        if(AppUtility.isInternetConnected()){
            ApiClient.getServices().deletePost(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object :Observer<Posts>{
                    override fun onComplete() {

                    }

                    override fun onSubscribe(d: Disposable) {

                    }

                    override fun onNext(t: Posts) {
                        deletePost(id)
                    }

                    override fun onError(e: Throwable) {
                        Log.d("TAG",e.message)
                    }

                })
        }else{
            ThisApplication.showToast(ThisApplication.getAppInstance().getString(R.string.check_internet_connection))
        }
    }



    private fun insertAllRecordsToDb(posts : List<Posts>){
        val postsDao=AppDatabase.invoke().postDao()
        val returnId=postsDao.insertAllPosts(posts)
    }

    private fun updatePost(post: Posts){
        val postsDao=AppDatabase.invoke().postDao()
        val returnId=postsDao.updatePost(post)
    }

    private fun deletePost(id:String){
        val postsDao=AppDatabase.invoke().postDao()
        var returnId=postsDao.deletePost(id)
    }

}