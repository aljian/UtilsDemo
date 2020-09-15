package com.lvj.utilsdemo

import com.lvj.utilsdemo.bean.UserInfo
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import kotlin.system.measureTimeMillis

fun main() {

    test1()

}

private fun test1() {
    val jsonStr =
        "[{\"name\":\"张三\",\"age\":\"23\",\"foods\":\"面包\"},{\"name\":\"李四\",\"age\":\"24\",\"foods\":\"苹果\"}]"

    val moshi = Moshi.Builder().build()
    val adapter = moshi.adapter<List<UserInfo>>(
        Types.newParameterizedType(
            List::class.java,
            UserInfo::class.java
        )
    )
    val list = adapter.fromJson(jsonStr)
    list?.forEach {
        println("name = ${it.name} age = ${it.age} food = ${it.food}")
    }
    println("---------------------------------------------")

    val mList = listOf(UserInfo("王五", 25), UserInfo("赵六", 26))
    val toJson = adapter.toJson(mList)
    println("toJson = $toJson")
}