package com.lvj.utilsdemo.bean

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class UserInfo(
    var name: String = "",
    var age: Int = 0,
    @Json(name = "foods") var food: String = ""
)
