package com.xiangxue.lib.kt.s04

import java.io.File

fun main() {
    val stu = Student()
    stu.add(100, 200)

    // KT本来就有
    val file = File("///")
    file.readText()
    file.show()
}

// 扩展 函数
fun Student.add(n1: Int, n2: Int) {
    println("结果：${n1 + n2}")
}

fun Student.show() {
    println("Student show")
}

// 给 java File 增加 扩展函数
fun File.show() {
    println("给 java File 增加 扩展函数")
}