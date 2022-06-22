package com.singerstone.test

/**
 * 父类成员变量初始化-父类构造函数-子类成员变量-子类构造函数
 */
class 构造函数 {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            Child("child construct")
        }
    }

}

abstract class Parent(name: String) {

    init {
        doSomething()
    }

    abstract fun doSomething()
}

class Child(private var name: String) : Parent(name) {

    var name2: String = "name2"

    init {
        println(name)
        println(name2)
    }

    override fun doSomething() {
        println("$name -")
        println("$name2 -")
        name = "rename"
        name2 = "rename2"
    }

}