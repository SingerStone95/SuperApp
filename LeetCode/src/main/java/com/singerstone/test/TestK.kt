package com.singerstone.test

import kotlinx.coroutines.*

class TestK {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val list= mutableListOf<String>()

            list.add("1")
            list.add("2")

            list.map {
                it+"124"
            }.forEach {
                print(it)
            }
//            print(list)

            var testK = TestK()
            var beforeTime = System.currentTimeMillis()
            // 2. 启动协程
            runBlocking {
                val one = async { testK.getResult(1000) }
                val two = async { testK.getResult(2000) }
                var before = System.currentTimeMillis()
                one.await()
                two.await()
                println("1->" + (System.currentTimeMillis() - before).toString() + "ms")
                
            }
            //保证程序不退出
            println("2->" + (System.currentTimeMillis() - beforeTime).toString() + "ms")

        }

    }

    private suspend fun getResult(delayT: Long): Int {
        withContext(Dispatchers.IO) {
            delay(delayT)
        }
        return 0
    }

}