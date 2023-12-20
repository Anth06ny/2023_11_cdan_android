package com.example.a2023_11_cdan_android.exo

import java.util.Random

fun main() {
//    var car = CarBean("Seat", "Leon")
//    car.color = "grise"
//    println("La voitures est une ${car.marque} de couleur ${car.color}")

//    var house = HouseBean(10,20,"Bleu")
//    house.print()
    val randomName = RandomName()
    randomName.add("bobby")
    repeat(10) {
        println(randomName.nextDiff() + " ")
    }

}

/* -------------------------------- */
// Compose
/* -------------------------------- */

const val LONG_TEXT = """Le Lorem Ipsum est simplement
    du faux texte employé dans la composition
    et la mise en page avant impression.
    Le Lorem Ipsum est le faux texte standard
    de l'imprimerie depuis les années 1500"""
data class PictureData(val url: String, val text: String, val longText: String)

//jeu de donnée
val pictureList = arrayListOf(PictureData("https://picsum.photos/200", "ABCD", LONG_TEXT),
    PictureData("https://picsum.photos/201", "BCDE", LONG_TEXT),
    PictureData("https://picsum.photos/202", "CDEF", LONG_TEXT),
    PictureData("https://picsum.photos/203", "EFGH", LONG_TEXT)
)
/* -------------------------------- */
// USER
/* -------------------------------- */
data class UserBean(
    val age: Int,
    val coord: Coord?,
    val id: Int,
    val img: String?,
    val login: String,
    val name: String
)

data class Coord(
    val mail: String?,
    val phone: String?
)

/* -------------------------------- */
// API Wheater
/* -------------------------------- */

data class WeatherAroundResult(var list : List<WeatherBean>)

data class WeatherBean(var main : TempBean,var wind : WindBean,var name : String, var weather : List<DescriptionBean>)
data class TempBean(var temp: Double)
data class WindBean(var speed: Double)

data class DescriptionBean(
    var description: String,
    var icon: String,
    var main: String
)

/* -------------------------------- */
// Exo
/* -------------------------------- */

class RandomName(){

    private val list = arrayListOf("Bobby", "Titi", "Gustave")
    private var oldValue = ""

    fun add(name:String?)=  if(!name.isNullOrBlank() && name !in list) list.add(name) else false

    fun next() = list.random()
    fun next2() = Pair(nextDiff(), nextDiff())


    fun nextDiffv2(): String {
        return list.filter { it != oldValue }.random().also { oldValue = it  }
    }
    fun nextDiff(): String {
        var newValue = next()
        while(newValue == oldValue){
            newValue = next()
        }
        oldValue = newValue

        return newValue
    }


}

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

data class MessageBean(var pseudo: String, var message : String)
