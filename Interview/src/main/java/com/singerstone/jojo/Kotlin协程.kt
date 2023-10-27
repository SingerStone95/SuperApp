package com.singerstone.jojo

import com.singerstone.InterfaceTest
import com.singerstone.cas.SleepUtil
import kotlinx.coroutines.*
import java.util.concurrent.Executors
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

            var interfaceTest = InterfaceTest {
                it.toInt()
            }
            interfaceTest.func("123")
            val list = mutableListOf<String>()

            list.add("1")
            list.add("2")
            list.map {
                it + "124"
            }.forEach {
                println(it)
            }

            GlobalScope.launch() {

            }
            val myDispatcher = Executors.newSingleThreadExecutor { r -> Thread(r, "MyThread") }
                .asCoroutineDispatcher()

            var testK = Kotlin协程()
            var beforeTime = System.currentTimeMillis()
            // 2. 启动协程
            runBlocking {
                val one = async(myDispatcher) {
                    println(Thread.currentThread().name + " one")
                    testK.getResult(1000)
                }
                val two = async(myDispatcher) {
                    println(Thread.currentThread().name + " two")
                    testK.getResult(500)
                }
                var three = async {
                    println(Thread.currentThread().name + " three")
                    testK.reqNetWorkSync()
                }
                var before = System.currentTimeMillis()
                // await 才会触发上边的协程执行
               // println("one.await=" + one.await())
                println("two.await=" + two.await())
                println("three.await=" + three.await())
                println("1->" + (System.currentTimeMillis() - before).toString() + "ms")
            }

            // 这一步为了关闭线程池
            myDispatcher.close()


        }

    }

    private fun reqNetWork(callBack: CallBack) {
        thread {
            SleepUtil.sleep(2000)
            callBack.onSuccess()
            //callBack.onFailure()
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