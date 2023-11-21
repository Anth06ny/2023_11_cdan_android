package com.example.a2023_11_cdan_android.exo

import java.util.Random

fun main() {
//    var car = CarBean("Seat", "Leon")
//    car.color = "grise"
//    println("La voitures est une ${car.marque} de couleur ${car.color}")

//    var house = HouseBean(10,20,"Bleu")
//    house.print()

    val therm = ThermometerBean(-20, 50, 0)
    println("Température de ${therm.value}") // 0
    therm.value = 18
    println("Température de ${therm.value}") // 18
    therm.value = -35
    //La valeur dépassant la borne min, on garde le min
    println("Température de ${therm.value}") // -20

    //Pour les plus rapides :
    val therm2 = ThermometerBean(-20, 50, 80)
    println("Température de ${therm2.value}") // 50

}

/* -------------------------------- */
// API Wheater
/* -------------------------------- */

data class WeatherBean(var main : TempBean,var wind : WindBean,var name : String)
data class TempBean(var temp: Double)
data class WindBean(var speed: Double)

/* -------------------------------- */
// Exo
/* -------------------------------- */

class ThermometerBean(val min: Int, val max: Int, value: Int) {
    var value = value.coerceIn(min, max)
        set(value) {
            field = value.coerceIn(min, max)
        }

}


class PrintRandomIntBean(val max: Int) {
    private val random = Random()

    init {
        println(random.nextInt(max))
        println(random.nextInt(max))
        println(random.nextInt(max))
    }

    constructor() : this(100) {
        println(random.nextInt(max))
    }

}

class HouseBean(largeur: Int, longueur: Int, var couleur: String) {
    var surface = largeur * longueur

    fun print() = println("La maison $couleur fait $surface")
}

data class CarBean(var marque: String, var model: String?) {
    var color = ""
}

