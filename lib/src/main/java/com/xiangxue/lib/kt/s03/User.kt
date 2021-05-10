package com.xiangxue.lib.kt.s03

data class User(val id: Int, val name: String, val sex: Char)

fun main() {
    val u = User(1, "Derry", 'M')
    val (v1, v2, v3) = u.copy()
    println("v1:$v1, v2:$v2, v3: $v3")
}