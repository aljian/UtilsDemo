package com.lvj.utilsdemo

import org.junit.Test

class Demo {
    @Test
    fun demo() {
        val mData = mutableListOf<User>()
        mData.add(User("111", 1))
        mData.add(User("222", 2))
        mData.add(User("333", 3))
        mData.add(User("444", 3))
        mData.add(User("555", 2))
        mData.add(User("666", 1))
        mData.add(User("777", 2))
        mData.add(User("888", 3))
        mData.add(User("999", 1))
        mData.add(User("000", 3))

        val mSelected = mutableListOf<User>()
        mData.forEach {
            if (it.age == 2) {
                return@forEach
            }
            mSelected.add(it)
        }
        println("mSelected = $mSelected")
    }

    @Test
    fun demo12(){
        val a = 123
        println("结果 = ${a shl 1 == 0}")
    }

}

data class User(
    var name: String? = "",
    var age: Int? = 0
)