package com.example.a2023_11_cdan_android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.a2023_11_cdan_android.ui.Routes
import com.example.a2023_11_cdan_android.ui.screens.DetailScreen
import com.example.a2023_11_cdan_android.ui.screens.MexicanFoodScreen
import com.example.a2023_11_cdan_android.ui.screens.SearchScreen
import com.example.a2023_11_cdan_android.ui.theme._2023_11_cdan_androidTheme
import com.example.a2023_11_cdan_android.viewmodel.MainViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            _2023_11_cdan_androidTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = Color.LightGray) {
                    //AppNavigation()
                    VCardUITest()
                }
            }
        }
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun DetailScreenPreview() {
    _2023_11_cdan_androidTheme {
        Surface(modifier = Modifier.fillMaxWidth(), color = Color.LightGray) {
            VCardUITest()
        }
    }
}

@Composable
fun VCardUITest(modifier: Modifier = Modifier) {

    val isError = true

    Column {
        Card(
            modifier = Modifier
                .border(
                    8.dp,
                    color = if (isError) Color.Red else MaterialTheme.colorScheme.primary,
                    shape = RoundedCornerShape(4.dp)
                )
                .fillMaxWidth(1f)
                .layoutId("card_root")
        ) {
            Box(modifier = modifier.padding(8.dp)) {
                val ressourceId = android.R.drawable.ic_dialog_info
                Image(
                    contentScale = ContentScale.FillWidth,
                    painter = painterResource(id = ressourceId),
                    contentDescription = "",
                    modifier = Modifier
                        .fillMaxWidth(1f)
                        .layoutId("image_background")
                )
                if (isError) {
                    Spacer(
                        modifier = modifier
                            .matchParentSize()
                            .background(Color.Yellow.copy(alpha = 0.2f))
                    )
                }
            }
        } // Card for Vcard content.
    } // Outter box (for padding)
}

@Composable
fun AppNavigation() {

    val navController: NavHostController = rememberNavController()
    val viewModel: MainViewModel = androidx.lifecycle.viewmodel.compose.viewModel()

    //Import version avec Composable
    NavHost(navController = navController, startDestination = Routes.SearchScreen.route) {
        //Route 1 vers notre SearchScreen
        composable(Routes.SearchScreen.route) {
            //on peut passer le navController à un écran s'il déclenche des navigations
            SearchScreen(navHostController = navController, viewModel = viewModel)
        }

        //Route 2 vers un écran de détail
        composable(
            route = Routes.DetailScreen.route,
            arguments = listOf(navArgument("data") { type = NavType.IntType })
        ) {
            val position = it.arguments?.getInt("data", 0) ?: 0
            DetailScreen(position = position, navHostController = navController, viewModel = viewModel)
        }

        //Route 2 vers un écran de détail
        composable(
            route = Routes.MexicanScreen.route,
            arguments = listOf(navArgument("foodId") { type = NavType.StringType })
        ) {
            val foodId = it.arguments?.getString("foodId", "") ?: ""
            MexicanFoodScreen(foodId)
        }
    }
}




