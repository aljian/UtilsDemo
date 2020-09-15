package com.lvj.utilsdemo.retrofit.api

import retrofit2.http.*

interface NetApiService {

    @POST("user/login")
    @FormUrlEncoded
    suspend fun login(@Field("username") phone: String, @Field("password") code: String): BaseEntity<UserEntity>

    @GET("article/list/{page}/json")
    suspend fun getHomeArticle(@Path("page") page: Int): BaseEntity<ArticleEntity>

}