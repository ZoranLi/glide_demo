package com.xiangxue.kotlinproject.modules.register

import android.os.Bundle
import android.widget.Toast
import com.xiangxue.kotlinproject.R
import com.xiangxue.kotlinproject.base.BaseActivity
import com.xiangxue.kotlinproject.entity.LoginRegisterResponse
import com.xiangxue.kotlinproject.modules.register.inter.RegisterPersenter
import com.xiangxue.kotlinproject.modules.register.inter.RegisterView
import kotlinx.android.synthetic.main.activity_user_register.*

class RegisterActivity : BaseActivity<RegisterPersenter>(), RegisterView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        hideActionBar()

        setContentView(R.layout.activity_user_register)

        user_register_bt.setOnClickListener {
            //调用注册
            val usernameStr = user_phone_et.text.toString()
            val passwordStr = user_password_et.text.toString()
            presenter.registerWanAndroid(this@RegisterActivity, usernameStr, passwordStr, passwordStr)
        }
    }

    override fun createP(): RegisterPersenter = RegisterPresenterImpl(this)

    override fun recycle() {
        presenter.unAttachView()
    }

    override fun registerSuccess(registerBean: LoginRegisterResponse?) {
        Toast.makeText(this, "注册成功😀", Toast.LENGTH_SHORT).show()
    }

    override fun registerFailed(errorMsg: String?) {
        Toast.makeText(this, "注册失败 ~ 呜呜呜", Toast.LENGTH_SHORT).show()
    }
}