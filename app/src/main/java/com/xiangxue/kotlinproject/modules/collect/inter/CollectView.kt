package com.xiangxue.kotlinproject.modules.collect.inter

import android.app.Application
import com.xiangxue.kotlinproject.databse.Student

interface CollectView {

    // 显示数据  --- 》 View

    fun showResultSuccess(result: List<Student> ?)

    fun showResult(result: Boolean)
}