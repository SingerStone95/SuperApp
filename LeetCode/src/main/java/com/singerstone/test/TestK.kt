package com.singerstone.test

import kotlinx.coroutines.*
import java.lang.Thread.sleep

class TestK {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            var testK = TestK()
            val scope = MainScope()
            // 2. 启动协程
            scope.launch(Dispatchers.Unconfined) {
                val one = async { testK.getResult(1000) }
                val two = async { testK.getResult(2000) }
                var before = System.currentTimeMillis()
                //one.await()
                two.await()
                System.out.println((System.currentTimeMillis() - before).toString() + "ms")
            }
            //保证程序不退出
            sleep(4000)

        }

    }

    private suspend fun getResult(delayT: Long): Int {
        delay(delayT)
        return 0
    }

}