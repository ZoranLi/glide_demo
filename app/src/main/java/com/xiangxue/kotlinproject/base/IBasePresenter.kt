package com.xiangxue.kotlinproject.base

/**
 * 同学们这是 “所有P层的最上层父类“  最上层标准
 */
interface IBasePresenter {

    // fun attachView()

    // 试图离开了 （Activity， Fragment）  离开了   销毁的事情
    fun unAttachView()
}