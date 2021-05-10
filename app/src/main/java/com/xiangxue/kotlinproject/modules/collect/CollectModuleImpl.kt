package com.xiangxue.kotlinproject.modules.collect

import android.util.Log
import com.xiangxue.kotlinproject.config.Flag
import com.xiangxue.kotlinproject.data_model.local.LocalRoomRequestManager
import com.xiangxue.kotlinproject.databse.Student
import com.xiangxue.kotlinproject.modules.collect.inter.CollectModel
import com.xiangxue.kotlinproject.modules.collect.inter.CollectPresenter

// M impl
class CollectModuleImpl : CollectModel {

    override fun requestInsert(
        listener: CollectPresenter.OnCollectListener,
        vararg students: Student
    ) {
        LocalRoomRequestManager.getInstance().insertStudents(*students)
        // TODO 伪代码：例如：这里还可以做很多的逻辑，等等，最终确保此操作没有问题，就返回true
        //  ......
        listener.showIUD(true)
    }

    override fun requestUpdate(
        listener: CollectPresenter.OnCollectListener,
        vararg students: Student
    ) {
        LocalRoomRequestManager.getInstance().updateStudents(*students)
        // TODO 伪代码：例如：这里还可以做很多的逻辑，等等，最终确保此操作没有问题，就返回true
        //  ......
        listener.showIUD(true)
    }

    override fun requestDelete(
        listener: CollectPresenter.OnCollectListener,
        vararg students: Student
    ) {
        LocalRoomRequestManager.getInstance().deleteStudents(*students)
        // TODO 伪代码：例如：这里还可以做很多的逻辑，等等，最终确保此操作没有问题，就返回true
        //  ......
        listener.showIUD(true)
    }

    override fun requestDeleteAll(listener: CollectPresenter.OnCollectListener) {
        LocalRoomRequestManager.getInstance().deleteAllStudent()
        // TODO 伪代码：例如：这里还可以做很多的逻辑，等等，最终确保此操作没有问题，就返回true
        //  ......
        listener.showIUD(true)
    }

    override fun requestQueryAll(listener: CollectPresenter.OnCollectResponseListener) {
        val result = LocalRoomRequestManager.getInstance().queryAllStudent()
        Log.d(Flag.TAG, "requestQueryAll: result$result")
        listener.showResultSuccess(result)
    }
}