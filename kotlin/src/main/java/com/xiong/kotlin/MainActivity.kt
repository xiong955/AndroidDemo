package com.xiong.kotlin

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val str:String = "123123"
    private var mInt:Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
    }

    private fun initView() {
        tv.text = str
        tv.setOnClickListener{
            Toast.makeText(this,"tv",Toast.LENGTH_SHORT).show()
        }
    }

}
