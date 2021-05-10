package com.xiangxue.kotlinproject.modules.login.inter

import android.content.Context

// Module
interface LoginModule {

    // 取消请求 动作
    fun cancelRequest()

    // 登录
    fun login(
        context: Context,
        username: String,
        password: String,

        // 接口回调，把data 结果，给P层
        onLoginListener: LoginPresenter.OnLoginListener
        )

}