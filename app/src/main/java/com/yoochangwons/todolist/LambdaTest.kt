package com.yoochangwons.todolist

fun main(array: Array<String>) {

    println(funs { sum(1,2) + 1 })
    val test = {a: Int, b: Int -> a + b}
    println(test(1,2))
}

private fun sum(a: Int, b: Int): Int {
    return a + b
}

private fun funs(a: (Int) -> Int) {
    a.invoke(3)
}