package com.xiangxue.kotlinproject

import android.app.Application
import android.util.Log
import com.xiangxue.kotlinproject.config.Flag
import com.xiangxue.kotlinproject.databse.StudentDatabase

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        Log.d(Flag.TAG, "MyApplication onCreate run")

        // 初始化
        StudentDatabase.getDatabase(this)
    }

}