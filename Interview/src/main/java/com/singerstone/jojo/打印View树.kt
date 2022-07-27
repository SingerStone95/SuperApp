package com.singerstone.jojo

class 打印View树 {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            var root = makeViewTree()
            printViewTree(root, 0)
        }

        private fun printViewTree(root: ViewGroup, level: Int) {
            for (i in 0..level) {
                if (i==level){
                    print("+- ")
                }else{
                    print("  |")
                }
            }
            println(root.id)
            for (view in root.childView) {
                if (view is ViewGroup) {
                    printViewTree(view, level + 1)
                }
            }
        }

        private fun makeViewTree(): ViewGroup {
            val root = ViewGroup(0)
            for (i in 1..20) {
                val v1 = ViewGroup(1)
                root.childView.add(v1)
                var id = 6
                if (i % 2 == 0) {
                    val v2 = ViewGroup(2)
                    v1.childView.add(v2)
                    if (i % 4 == 0) {
                        val v3 = ViewGroup(3)
                        v2.childView.add(v3)
                    }
                }
            }
            return root
        }
    }


}

class ViewGroup(id: Int) : View(id) {
    var childView = mutableListOf<View>()
}

open class View(public val id: Int) {

}