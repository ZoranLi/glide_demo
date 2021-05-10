package com.xiangxue.kotlinproject.modules.register.inter

import com.xiangxue.kotlinproject.entity.LoginRegisterResponse

interface RegisterView {

    // 成功 失败 显示 到 Activty

    fun registerSuccess(registerBean: LoginRegisterResponse?)

    fun registerFailed(errorMsg: String?)


}