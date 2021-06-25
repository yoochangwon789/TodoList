package com.yoochangwons.todolist

fun main(array: Array<String>) {

    print(funs { sum(1,2) + 1 })
}

private fun sum(a: Int, b: Int): Int {
    return a + b
}

private fun funs(a: (Int) -> Int) {
    a.invoke(3)
}