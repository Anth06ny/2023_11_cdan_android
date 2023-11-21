package com.example.a2023_11_cdan_android.exo

fun main() {
    //var html = WeatherAPI.sendGet("https://www.google.fr")
//    var html = WeatherAPI.sendGet("https://api.openweathermap.org/data/2.5/weather?q=Toulouse&appid=b80967f0a6bd10d23e44848547b26550&units=metric&lang=fr")

    var cityName = "Nice"
    var weather = WeatherAPI.loadWeather(cityName)
    println("Il fait ${weather.main.temp}° à ${weather.name} avec un vent de ${weather.wind.speed} m/s")

    var user = WeatherAPI.loadUser()

    println("${user.name} ${user.age} ses contactes : \nmail : ${user.coord?.mail ?: "-"}" +
            "\nPhone : ${user.coord?.phone ?: "-"}")
}