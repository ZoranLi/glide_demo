package com.xiangxue.kotlinproject.modules.login

import android.content.Context
import com.xiangxue.kotlinproject.entity.LoginRegisterResponse
import com.xiangxue.kotlinproject.modules.login.inter.LoginPresenter
import com.xiangxue.kotlinproject.modules.login.inter.LoginView

// Presenter 层  的 实现
class LoginPresenterImpl (var loginView: LoginView ?) : LoginPresenter
, LoginPresenter.OnLoginListener
{

    // Java private LoginPresenterImpl() {}

    // 需要 M 去请求服务器
    private val loginModel: LoginModelImpl = LoginModelImpl()

    // 需要 V 去更新 Activity/Fragment

    override fun loginAction(context: Context, username: String, password: String) {
        // TODO
        // 可以做很多的事情
        // 可以省略很多代码
        // 很多的校验
        // ....

        loginModel.login(context, username, password, this)
    }

    // Base Presenter 的
    override fun unAttachView() {
        loginView = null
        loginModel.cancelRequest() // 取消请求
    }

    // 接收 Model 的结果集
    override fun loginSuccess(loginBean: LoginRegisterResponse?) {
        // TODO
        // 可以做很多的事情
        // 可以省略很多代码
        // 很多的校验
        // ....

        loginView?.loginSuccess(loginBean)
    }

    override fun loginFialure(erroeMsg: String?) {
        // TODO
        // 可以做很多的事情
        // 可以省略很多代码
        // 很多的校验
        // ....

        loginView?.loginFialure(erroeMsg)
    }

}