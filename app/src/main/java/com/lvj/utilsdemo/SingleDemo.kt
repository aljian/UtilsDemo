package com.lvj.utilsdemo

/*  1  */
object SingleDemo

/* 2  */
class SingleDemo2 private constructor() {
    companion object {
        private var instance: SingleDemo2? = null
            get() {
                if (field == null) {
                    field = SingleDemo2()
                }
                return field
            }

        fun get(): SingleDemo2 {
            //不能使用getInstance()方法 因为伴生对象声明时内部已有 getInstance()方法
            return instance!!
        }

    }

    fun dotest() {
        println("hahahhaah")
    }
}

/*  3  */
class SingleDemo3 private constructor() {
    companion object {
        val instance: SingleDemo3 by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            SingleDemo3()
        }
    }

}

/*  4  */
class SingleDemo4 private constructor(private val position: Int) {

    companion object {
        @Volatile
        private var instance: SingleDemo4? = null
        fun getInstance(position: Int) = instance ?: synchronized(this) {
            instance ?: SingleDemo4(position).also { instance = it }
        }

    }

    fun doTest() {
        println("SingleDemo4 position = $position")
    }

}

