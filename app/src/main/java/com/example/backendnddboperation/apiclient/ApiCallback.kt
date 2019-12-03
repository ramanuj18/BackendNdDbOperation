package com.example.backendnddboperation.apiclient

import com.google.gson.JsonObject
import io.reactivex.disposables.Disposable

/**
 * created by Ramanuj Kesharawani on 23/11/19
 */
interface ApiCallback {
    fun onSubscribe(d: Disposable)
    fun onNext(jsonObject: JsonObject)
    fun onError(e:Throwable)
    fun onComplete()
}