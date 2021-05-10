package com.xiangxue.lib.kt.s02

fun show(name: String) {
    println("name:$name")
}

fun show2(name: String) {
    println("name:$name")
}

fun add(n1: Int, n2: Int) {
    println("resut:${n1 + n2}")
}

fun `showTest`() {
    println("showTest")
}

fun `   `(sex: Char) {
    println("sex:$sex")
}

fun `4325436465375`(name: String) {

}

fun main() {
    `showTest`()
    `   `('M')
    `4325436465375`("Derry")
}