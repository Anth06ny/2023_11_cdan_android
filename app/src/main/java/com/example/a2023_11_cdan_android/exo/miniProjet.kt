package com.example.a2023_11_cdan_android.exo

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


fun getWeathers(vararg cities:String) = flow<WeatherBean> {
    cities.forEach {
        emit(WeatherAPI.loadWeather(it))
        delay(500)
    }
}


fun main() {

    runBlocking {

        launch {
            getWeathers("Toulouse", "Nice", "Paris", "Lyon")
                .filter { it.wind.speed < 5 }
                // Gestion des erreurs
                .catch { e -> println("Erreur capturée: ${e.message}") }
                .collect {
                    println("it")
                } // Démarre la collecte du Flow
        }
    }

    println("fin")

}

