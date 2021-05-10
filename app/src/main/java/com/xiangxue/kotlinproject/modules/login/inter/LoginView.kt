package com.xiangxue.kotlinproject.modules.login.inter

import com.xiangxue.kotlinproject.entity.LoginRegisterResponse

// View层
interface LoginView {

    // 把结果显示到  Activity / Fragment

    fun loginSuccess(loginBean: LoginRegisterResponse ?)

    fun loginFialure(erroeMsg: String  ?)
}