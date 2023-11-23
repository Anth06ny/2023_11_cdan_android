package com.example.a2023_11_cdan_android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.a2023_11_cdan_android.ui.Routes
import com.example.a2023_11_cdan_android.ui.screens.DetailScreen
import com.example.a2023_11_cdan_android.ui.screens.SearchScreen
import com.example.a2023_11_cdan_android.ui.theme._2023_11_cdan_androidTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            _2023_11_cdan_androidTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = Color.LightGray) {
                    AppNavigation()
                }
            }
        }
    }
}

@Composable
fun AppNavigation() {

    val navController : NavHostController = rememberNavController()

    //Import version avec Composable
    NavHost(navController = navController, startDestination = Routes.SearchScreen.route) {
        //Route 1 vers notre SearchScreen
        composable(Routes.SearchScreen.route) {
            //on peut passer le navController à un écran s'il déclenche des navigations
            SearchScreen(navHostController = navController)
        }

        //Route 2 vers un écran de détail
        composable(
            route = Routes.DetailScreen.route,
            arguments = listOf(navArgument("data") { type = NavType.IntType })
        ) {
            val position = it.arguments?.getInt("data", 0 ) ?: 0
            DetailScreen(position = position, navHostController = navController)
        }
    }
}




