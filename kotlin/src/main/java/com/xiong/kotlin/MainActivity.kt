package com.xiong.kotlin

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.xiong.entity.XBean

class MainActivity : AppCompatActivity() {

    private lateinit var mAdapter: RvAdapter

    private var mList: List<XBean> = listOf(
            XBean.newIntance(0, "QQ", "10000"),
            XBean.newIntance(1, "支付宝", "10001"),
            XBean.newIntance(2, "微信", "10002")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
    }

    private fun initView() {
        val mRv: RecyclerView = findViewById(R.id.kotlin_rv)
        mAdapter = RvAdapter(mList)
        mRv.layoutManager = LinearLayoutManager(this)
        mRv.adapter = mAdapter
    }

    class RvAdapter(private val items: List<XBean>) : RecyclerView.Adapter<RvAdapter.ViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent?.context).inflate(R.layout.item_main, parent, false)
            return ViewHolder(view)
        }

        override fun getItemCount(): Int = items.size

        override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
            holder?.textView?.text = items[position].name
            holder?.copyIv?.setOnClickListener {
                val cmb = holder.itemView.context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                cmb.primaryClip = ClipData.newPlainText(null,items[position].account)
                Toast.makeText(holder.itemView.context,"复制成功",Toast.LENGTH_SHORT).show()
            }
        }

        class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val textView: TextView = itemView.findViewById(R.id.item_tv_tit)
            val copyIv : ImageView = itemView.findViewById(R.id.item_iv_copy)
        }

    }
}
