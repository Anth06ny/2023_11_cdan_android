package com.example.a2023_11_cdan_android.exo

import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.Request

fun main() {
    println(WeatherAPI.loadWeatherAround("Toulouse"))
}

object WeatherAPI {

    //Attribut instancié 1 seule fois car c'est un singleton
    //Et uniquement à la 1er utilisation (Lazy Loading)
    private val client = OkHttpClient()
    val gson = Gson()

    fun loadWeatherAround(cityName: String): List<WeatherBean> {
        if(cityName.length < 3) {
            throw Exception("Il faut au moins 3 caractères")
        }
        //Eventuel contrôle
        //Réaliser la requête.
        val json: String = sendGet("https://api.openweathermap.org/data/2.5/find?q=$cityName&cnt=5&appid=b80967f0a6bd10d23e44848547b26550&units=metric&lang=fr")

        //Parser le JSON avec le bon bean et GSON
        val data  = gson.fromJson(json, WeatherAroundResult::class.java)

        //Retourner la donnée
        return data.list
    }

    fun loadWeather(cityName: String): WeatherBean {
        //Eventuel contrôle
        //Réaliser la requête.
        val json: String = sendGet("https://api.openweathermap.org/data/2.5/weather?q=$cityName&appid=b80967f0a6bd10d23e44848547b26550&units=metric&lang=fr")

        //Parser le JSON avec le bon bean et GSON
        val data : WeatherBean = gson.fromJson(json, WeatherBean::class.java)

        //Eventuel contrôle ou extraction de données

        //Retourner la donnée
        return data
    }

    fun loadUser(): UserBean {
        //Eventuel contrôle
        //Réaliser la requête.
        val json: String = sendGet("https://www.amonteiro.fr/api/randomuser")

        //Parser le JSON avec le bon bean et GSON
        return gson.fromJson(sendGet("https://www.amonteiro.fr/api/randomuser"), UserBean::class.java)
    }



    fun sendGet(url: String): String {
        println("url : $url")
        //Création de la requête
        val request = Request.Builder().url(url).build()
        //Execution de la requête
        return client.newCall(request).execute().use {
            //Analyse du code retour
            if (!it.isSuccessful) {
                throw Exception("Réponse du serveur incorrect :${it.code}")
            }
            //Résultat de la requête
            it.body.string()
        }
    }
}