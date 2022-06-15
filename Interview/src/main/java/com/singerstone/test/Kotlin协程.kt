package com.singerstone.test

import com.singerstone.cas.SleepUtil
import kotlinx.coroutines.*
import kotlin.concurrent.thread
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

/**
 * 协程的测试写这里
 */
class Kotlin协程 {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val list= mutableListOf<String>()

            list.add("1")
            list.add("2")

            list.map {
                it + "124"
            }.forEach {
                println(it)
            }


            var testK = Kotlin协程()
            var beforeTime = System.currentTimeMillis()
            // 2. 启动协程
            runBlocking {
                val one = async() { testK.getResult(1000) }
                val two = async { testK.getResult(500) }
                var three = async { testK.reqNetWorkSync() }
                var before = System.currentTimeMillis()
                println(one.await())
                println(two.await())
                println(three.await())
                println("1->" + (System.currentTimeMillis() - before).toString() + "ms")
            }


        }

    }

    private fun reqNetWork(callBack: CallBack) {
        thread {
            SleepUtil.sleep(2000)
            callBack.onFailure()
        }

    }

    private suspend fun reqNetWorkSync(): Int {
        var result = suspendCancellableCoroutine<Int> {
            reqNetWork(object : CallBack {
                override fun onSuccess() {
                    it.resume(0)
                }

                override fun onFailure() {
                    it.resumeWithException(RuntimeException("exception"))
                }

            })
        }
        println("reqNetWorkSync end")
        return result
    }

    private suspend fun getResult(delayT: Long) = withContext(Dispatchers.IO) {
        delay(delayT)
        println("getResult $delayT")
        return@withContext delayT
    }


    interface CallBack {
        fun onSuccess()
        fun onFailure()
    }
}