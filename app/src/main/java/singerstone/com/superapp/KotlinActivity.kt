package singerstone.com.superapp

import android.app.Activity
import android.os.Bundle
import android.widget.Toast
import singerstone.com.superapp.utils.L

class KotlinActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kotlin)
        showToast("hello")
        val str: String? = null
        val length:Int?=str?.length
        val xx:Boolean= length?:0>0
        L.i("$xx")


    }

    fun showToast(s: String) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show()
    }

}
