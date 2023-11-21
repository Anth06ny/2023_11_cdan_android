package com.example.a2023_11_cdan_android.exo

fun main() {
    exo1()
}
fun exo1(){
    val lower: (String) -> Unit = { s: String ->    println(s.lowercase()) }
    val lowerV2: (String) -> Unit = { s -> println(s.lowercase()) }
    val lowerV3: (String) -> Unit = { println(it.lowercase()) }
    val lowerV4 = { s: String -> println(s.lowercase()) }

    val max : (Int, Int) -> Int = {a,b -> Math.max(a, b)}
    println(max(3,5))

    val reverse: (String) -> String = { it:String -> it.reversed()}

    val minToHour = { it:Int -> Pair(it/60, it%60) }
    val res = minToHour(150)
    println("${res.first}:${res.second}")
}