package com.example.a2023_11_cdan_android.exo


fun main() {


    var v1 = "toto"
    println(v1.uppercase())

    var v2: String? = "toto"
    println(v2?.uppercase())
    v2 = null

    var v3: String? = null
    println(v3?.uppercase())

    var v4 = v3 + v3

    var myForm = v2?.trim()?.uppercase() ?: "-"

    println(min(3, 4, 5))

    var res = boulangerie(nbSandwitch = 5)

    println("res=$res")

    val nbBaguette = scanNumber("Combien de baguette : ")

    println(boulangerie(nbBaguette = nbBaguette))

}

fun scanText(question: String): String {
    print(question)
    return readlnOrNull() ?: "-"
}

fun scanNumber(question: String) = scanText(question).toIntOrNull() ?: 0


fun boulangerie(nbCroissant: Int = 0, nbBaguette: Int = 0, nbSandwitch: Int = 0): Double =
    nbBaguette * PRICE_BAGUETTE + nbCroissant * PRICE_CROISSANT + nbSandwitch * PRICE_SANDWITCH


fun min(a: Int, b: Int, c: Int = 5) =
    if (a < b && a < c) a else if (b < a && b < c) b else c

fun myPrint(texte: String) = println("#$texte#")

fun pair(c: Int) = c % 2 == 0

