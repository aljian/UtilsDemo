package com.lvj.utilsdemo.retrofit.api

import com.lvj.utilsdemo.IS_DEBUG
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

const val BASE_URL = "https://www.wanandroid.com/"

object NetworkApi {

    fun getApi(): NetApiService {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(getOkHttpClient())
            .build()
            .create(NetApiService::class.java)
    }

    private fun getOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .apply {
                if (IS_DEBUG) addInterceptor(LoggerInterceptor())
            }
            .build()
    }


}