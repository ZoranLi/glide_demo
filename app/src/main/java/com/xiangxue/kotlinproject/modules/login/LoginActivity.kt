package com.xiangxue.kotlinproject.modules.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.xiangxue.kotlinproject.MainActivity
import com.xiangxue.kotlinproject.R
import com.xiangxue.kotlinproject.base.BaseActivity
import com.xiangxue.kotlinproject.config.Flag
import com.xiangxue.kotlinproject.entity.LoginRegisterResponse
import com.xiangxue.kotlinproject.modules.login.inter.LoginPresenter
import com.xiangxue.kotlinproject.modules.login.inter.LoginView
import com.xiangxue.kotlinproject.modules.register.RegisterActivity
import kotlinx.android.synthetic.main.activity_user_login.*

// 违背单一的原则  C  V  M

// MVVM + JetPack

// 登录界面


// TODO  =========================================   View层  的 实现

class LoginActivity : BaseActivity<LoginPresenter>() , LoginView {

    // lateinit var p: LoginPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_login)

        hideActionBar()

        user_login_bt.setOnClickListener(onCLickLister)

        // p = LoginPresenterImpl(this)

        user_register_tv.setOnClickListener {
            val intent = Intent(this@LoginActivity, RegisterActivity::class.java)
            startActivity(intent)
        }
    }

    private val onCLickLister = View.OnClickListener { view ->
        // id = 内部根据= 知道你是 要 setId，  否则就是getId
        when(view.id) {
            // 登录
            R.id.user_login_bt -> {

                val userNameStr = user_phone_et.text.toString()
                val userPwdStr = user_password_et.text.toString()
                Log.d(Flag.TAG, "userName:$userNameStr,  userPwd:$userPwdStr")

                // 我执行 知道 P层
                presenter.loginAction(this@LoginActivity, userNameStr, userPwdStr)
            }

            // else->
        }
    }

    // 响应的两个函数
    override fun loginSuccess(loginBean: LoginRegisterResponse?) {
        Toast.makeText(this@LoginActivity, "登录成功😀", Toast.LENGTH_SHORT).show()

        val intent = Intent(this@LoginActivity,  MainActivity::class.java)
        startActivity(intent)
    }

    override fun loginFialure(erroeMsg: String?) {
        Toast.makeText(this@LoginActivity, "登录失败 ~ 呜呜呜", Toast.LENGTH_SHORT).show()
    }

    override fun createP(): LoginPresenter = LoginPresenterImpl(this)

    override fun recycle() {
        presenter.unAttachView()
    }
}
