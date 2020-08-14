package com.lvj.utilsdemo.retrofit.api

import retrofit2.http.GET
import retrofit2.http.Path

interface NetApiService {

//    @POST("")
//    @FormUrlEncoded
//    suspend fun login(@Field("phone") phone: String, @Field("code") code: String): BaseEntity<UserEntity>

    @GET("article/list/{page}/json")
    suspend fun getHomeArticle(@Path("page") page: Int): BaseEntity<ArticleEntity>

}