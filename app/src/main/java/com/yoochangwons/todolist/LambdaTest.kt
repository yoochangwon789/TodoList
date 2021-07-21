package com.yoochangwons.todolist

import java.util.*
import kotlin.random.Random

fun main(array: Array<String>) {

    println(funs { sum(1,2) + 1 })
    val test = {a: Int, b: Int -> a + b}
    println(test(1,2))

    val toUpperCase = {str: String -> str.uppercase() }
//    println(toUpperCase("str"))

    val strList = listOf<String>("a", "b", "c")
//    println(strList.map { s: String -> s.uppercase() })

    Random.nextInt(100).also {
        print("Random : $it")
    }
}

private fun sum(a: Int, b: Int): Int {
    return a + b
}

private fun funs(a: (Int) -> Int) {
    a.invoke(3)
}