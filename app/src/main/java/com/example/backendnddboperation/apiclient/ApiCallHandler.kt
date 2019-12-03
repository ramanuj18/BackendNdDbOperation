package com.example.backendnddboperation.apiclient

import android.util.Log
import com.example.backendnddboperation.utility.AppUtility
import com.google.gson.JsonObject
import com.google.gson.JsonPrimitive
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

/**
 * created by Ramanuj Kesharawani on 23/11/19
 */
class ApiCallHandler {

    companion object {
        fun postApiCall(url: String, requestBody: Any, apiCallback: ApiCallback) {
            ApiClient.getServices().postApiCall(url, AppUtility.getJsonObject(requestBody))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<JsonObject> {
                    override fun onSubscribe(d: Disposable) {
                        apiCallback.onSubscribe(d)
                    }

                    override fun onNext(t: JsonObject) {
                        apiCallback.onNext(t)
                    }

                    override fun onError(e: Throwable) {
                        apiCallback.onError(e)
                    }

                    override fun onComplete() {
                        apiCallback.onComplete()
                    }

                })
        }

        fun getApiCall(url:String,requestParam:HashMap<String,String>,apiCallback: ApiCallback){
            ApiClient.getServices().getApiCall(url)                // ApiClient.getServices().getEmployeeDetail("1")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object :Observer<JsonObject>{
                    override fun onComplete() {
                        apiCallback.onComplete()
                    }

                    override fun onSubscribe(d: Disposable) {
                        apiCallback.onSubscribe(d)
                    }

                    override fun onNext(t: JsonObject) {
                        apiCallback.onNext(t)
                    }

                    override fun onError(e: Throwable) {
                        apiCallback.onError(e)
                    }

                })
        }
    }

}