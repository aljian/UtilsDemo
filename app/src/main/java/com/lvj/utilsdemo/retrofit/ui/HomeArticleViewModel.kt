package com.lvj.utilsdemo.retrofit.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lvj.utilsdemo.retrofit.api.ArticleEntity
import com.lvj.utilsdemo.retrofit.api.ArticleListEntity
import com.lvj.utilsdemo.retrofit.api.BaseEntity
import com.lvj.utilsdemo.util.logi
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import kotlinx.coroutines.launch

class HomeArticleViewModel : ViewModel() {

    private val res: HomeArticleRepository by lazy { HomeArticleRepository() }

    val mResult = MutableLiveData<MutableList<ArticleListEntity>>()


    fun getHomeArticle(page: Int) {
        viewModelScope.launch {
            runCatching {
                res.getHomeArticle(page)
            }.onSuccess {
                if (it.dataRight()) {
                    val datas = it.data.datas
                    if (datas != null) {
                        mResult.postValue(datas!!)
                    }
                }

                val moshi = Moshi.Builder().build()
                val type = Types.newParameterizedType(BaseEntity::class.java, ArticleEntity::class.java)
                val jsonAdapter: JsonAdapter<BaseEntity<ArticleEntity>> = moshi.adapter(type)
                val fromJson = jsonAdapter.toJson(it)


                logi("测试的  moshi  ${fromJson}")
            }.onFailure {
                logi("failure ${it.localizedMessage}")
            }
        }
    }


}