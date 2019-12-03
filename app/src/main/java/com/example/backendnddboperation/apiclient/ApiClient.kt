package com.example.backendnddboperation.apiclient

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import com.google.gson.GsonBuilder
import com.google.gson.Gson



/**
 * created by Ramanuj Kesharawani on 22/11/19
 */
class ApiClient {

    companion object{
        private const val BASE_URL="https://jsonplaceholder.typicode.com/"
        private var retrofit:Retrofit?=null

        private fun getInstance():Retrofit{
            val builder by lazy { initOkHttpBuilder() }
            val client by lazy { builder.build()  }
            /*val gson = GsonBuilder()
                .setLenient()
                .create()*/
            if(retrofit==null) {
                retrofit=Retrofit.Builder().addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .baseUrl(BASE_URL)
                    .build()
            }
            return retrofit!!
        }

        private fun initOkHttpBuilder():OkHttpClient.Builder{
            val builder=OkHttpClient.Builder()
            builder.connectTimeout(60,TimeUnit.SECONDS)
            builder.readTimeout(60,TimeUnit.SECONDS)
            return builder
        }

        fun getServices():ServicesApi{
            return getInstance().create(ServicesApi::class.java)
        }
    }



}