package com.singerstone.jojo

/**
 * 已经记录在我的有道云笔记
 */
class ConcurrentModificationException原理 {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {

            val listeners = arrayListOf<CallBack>()
            listeners.add(object : CallBack {
                override fun callback() {
                    listeners.remove(this)
                }

            })
            listeners.add(object : CallBack {
                override fun callback() {

                }

            })
            listeners.add(object : CallBack {
                override fun callback() {
                }

            })

            ArrayList(listeners).forEach {
                it.callback()
            }
        }
    }

    interface CallBack {
        fun callback()
    }
}