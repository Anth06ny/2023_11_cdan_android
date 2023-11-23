package com.example.a2023_11_cdan_android.ui

sealed class Routes(val route: String) {
    //Route 1
    object SearchScreen : Routes("homeScreen")

    //Route 2 avec paramètre
    object DetailScreen : Routes("detailScreen/{data}") {
        fun addParam(position: Int) = "detailScreen/$position"
    }
}