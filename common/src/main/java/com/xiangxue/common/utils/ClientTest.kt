package com.xiangxue.common.utils

import android.os.Handler
import android.os.Looper
import android.os.Message
import android.util.Log

// 闭包 + 高阶 + 问题

fun main() {
    Handler(Looper.getMainLooper(), object : Handler.Callback {
        override fun handleMessage(msg: Message): Boolean {
            // 运行就崩溃
            // TODO("Not yet implemented")

            return true
        }
    })

    Handler(Looper.getMainLooper(), Handler.Callback {
        true
    })

    Thread{
        Log.e("", "main: ")
    }.start()

}