package com.lvj.utilsdemo.retrofit.api

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class BaseEntity<T>(
    var errorCode: Int = 1,// 0 成功  -1001 登录失效
    var errorMsg: String = "",
    var data: T
) {

    fun dataRight() = errorCode == 0

    fun needRelogin() = errorCode == -1001


//    override fun toString(): String {
//        return this.toGsonString()
//    }
}

@JsonClass(generateAdapter = true)
data class ArticleEntity(
    var curPage: Int = 0,
    var offset: Int = 0,
    var over: Boolean = false,
    var pageCount: Int = 0,
    var size: Int = 0,
    var total: Int = 0,
    var datas: MutableList<ArticleListEntity>? = mutableListOf()
)

@JsonClass(generateAdapter = true)
data class ArticleListEntity(
    var apkLink: String? = "",
    var audit: Int = 0,
    var author: String? = "",
    var canEdit: Boolean = false,
    var chapterId: Int = 0,
    var chapterName: String? = "",
    var collect: Boolean = false,
    var courseId: Int = 0,
    var desc: String? = "",
    var descMd: String? = "",
    var envelopePic: String? = "",
    var fresh: Boolean = false,
    var id: Int = 0,
    var link: String? = "",
    var niceDate: String? = "",
    var niceShareDate: String? = "",
    var origin: String? = "",
    var prefix: String? = "",
    var projectLink: String? = "",
    var publishTime: Long = 0,
    var realSuperChapterId: Int = 0,
    var selfVisible: Int = 0,
    var shareDate: Long = 0,
    var shareUser: String? = "",
    var superChapterId: Int = 0,
    var superChapterName: String? = "",
    var title: String? = "",
    var type: Int = 0,
    var userId: Int = 0,
    var visible: Int = 0,
    var zan: Int = 0,
    var tags: MutableList<ArticleTags>? = mutableListOf()
)

@JsonClass(generateAdapter = true)
data class ArticleTags(
    var name: String? = "",
    var url: String? = ""
)

data class UserEntity(
    var admin: Boolean? = false,
    var chapterTops: MutableList<Any>? = mutableListOf(),
    var coinCount: Int? = 0,
    var collectIds: MutableList<Int>? = mutableListOf(),
    var email: String? = "",
    var icon: String? = "",
    var id: Int? = 0,
    var nickname: String? = "",
    var password: String? = "",
    var publicName: String? = "",
    var token: String? = "",
    var type: Int? = 0,
    var username: String? = ""
)