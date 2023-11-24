package com.example.a2023_11_cdan_android.ui

import com.example.a2023_11_cdan_android.exo.MexicanFoodBean

sealed class Routes(val route: String) {
    //Route 1
    object SearchScreen : Routes("homeScreen")

    //Route 2 avec paramètre
    object DetailScreen : Routes("detailScreen/{data}") {
        fun addParam(position: Int) = "detailScreen/$position"
    }

    object MexicanScreen : Routes("mexicanScreen/{foodId}") {
        fun foodId(foodId: String) = if(foodId.isNotBlank()) "mexicanScreen/$foodId" else "mexicanScreen/-1"
    }
}