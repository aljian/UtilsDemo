package com.lvj.utilsdemo.retrofit.ui

import com.lvj.utilsdemo.retrofit.api.ArticleEntity
import com.lvj.utilsdemo.retrofit.api.BaseEntity
import com.lvj.utilsdemo.retrofit.api.NetworkApi

class HomeArticleRepository {


    suspend fun getHomeArticle(page: Int): BaseEntity<ArticleEntity> {
        return NetworkApi.getApi().getHomeArticle(page)
    }


}