package com.lvj.utilsdemo

import org.junit.Test

class Test {
    @Test
    fun demo() {

        println("13888888888 hidePhoneNum = ${hidePhoneNum("13888888888")}")
        println("null hidePhoneNum = ${hidePhoneNum(null)}")
    }

    fun hidePhoneNum(phone: String?): String? {
        return phone?.replaceRange(3, 7, "****")
    }
}