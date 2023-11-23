package com.example.a2023_11_cdan_android.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.a2023_11_cdan_android.exo.PictureData
import com.example.a2023_11_cdan_android.exo.pictureList

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
        Thread.sleep(5000)
        _myList.addAll(pictureList)
    }
}