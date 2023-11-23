package com.example.a2023_11_cdan_android.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a2023_11_cdan_android.exo.PictureData
import com.example.a2023_11_cdan_android.exo.WeatherAPI
import com.example.a2023_11_cdan_android.exo.WeatherBean
import com.example.a2023_11_cdan_android.exo.WindBean
import com.example.a2023_11_cdan_android.exo.pictureList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    //_myList modifiable à l'intérieur de la classe, myList non modifiable à l'exterieur
    private var _myList = mutableStateListOf<PictureData>()
    val myList: List<PictureData> = _myList

    var searchText by mutableStateOf("")
        private set

    fun uploadSearchText(newText: String) {
        searchText = newText
    }

    fun loadData() {//Simulation de chargement de donnée
        _myList.clear()

        viewModelScope.launch(Dispatchers.Default) {
           val list: List<WeatherBean> =  WeatherAPI.loadWeatherAround(searchText)

            val res: List<PictureData> = list.map {
                PictureData("https://openweathermap.org/img/wn/${it.weather.getOrNull(0)?.icon}@4x.png", it.name,
                    "Il fait ${it.main.temp}° à ${it.name} avec un vent de ${it.wind.speed} m/s")
            }

            _myList.addAll(res)
        }


    }
}