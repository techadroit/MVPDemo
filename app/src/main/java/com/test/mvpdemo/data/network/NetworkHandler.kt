package com.test.mvpdemo.data.network

import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


object NetworkHandler {

    val BASE_URL = "https://dl.dropboxusercontent.com/"

    lateinit private var retrofit: Retrofit

    fun init() {

        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val okHttpClient = OkHttpClient().newBuilder().addInterceptor(interceptor)
                .connectTimeout(60, TimeUnit.SECONDS).readTimeout(60, TimeUnit.SECONDS).build()

        retrofit = Retrofit.Builder()
                .baseUrl(NetworkHandler.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(Gson()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient).build()
    }


    fun getApiService(): ApiService {
        return retrofit.create(ApiService::class.java)
    }


}