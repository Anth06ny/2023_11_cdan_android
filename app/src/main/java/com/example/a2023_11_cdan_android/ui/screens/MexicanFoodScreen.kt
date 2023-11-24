package com.example.a2023_11_cdan_android.ui.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.placeholder
import com.example.a2023_11_cdan_android.R
import com.example.a2023_11_cdan_android.exo.MexicanFoodAPI
import com.example.a2023_11_cdan_android.exo.MexicanFoodBean
import com.example.a2023_11_cdan_android.ui.theme._2023_11_cdan_androidTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun MexicanFoodScreenPreview() {
    _2023_11_cdan_androidTheme {
        Surface(modifier = Modifier.fillMaxWidth(), color = Color.LightGray) {
            MexicanFoodScreen("4")
        }
    }
}

//Composable représentant l'ensemble de l'écran
@OptIn(ExperimentalFoundationApi::class, ExperimentalGlideComposeApi::class)
@Composable
fun MexicanFoodScreen(
    foodId: String,
    viewModel: MexicanFoodViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
) {
    Column {

        Text(
            text = viewModel.mexicanFood?.title ?: "-",
            color = MaterialTheme.colorScheme.primary,
            fontSize = 30.sp,
            modifier = Modifier.align(CenterHorizontally)
        )

        if (viewModel.runInProgress) {
            CircularProgressIndicator(
                modifier = Modifier.align(CenterHorizontally),
                color = MaterialTheme.colorScheme.primary //progress color
            )
        }

        if(viewModel.error.isNotBlank()){
            Text(
                text = viewModel.error,
                color = Color.White,
                fontSize = 20.sp,
                modifier = Modifier.background(Color.Red).fillMaxWidth().padding(8.dp)
            )
        }

        Row {
            Text(
                text = "Difficulté : ",
                color = Color.Blue,
                fontSize = 20.sp
            )

            Text(
                text = viewModel.mexicanFood?.difficulty ?: "-",
                color = Color.Black,
                fontSize = 20.sp

            )
        }

        Row {
            Text(
                text = "Description : ",
                color = Color.Blue,
                fontSize = 14.sp
            )

            Text(
                text = viewModel.mexicanFood?.description ?: "-",
                color = Color.Black,
                fontSize = 14.sp

            )
        }

        GlideImage(
            model = viewModel.mexicanFood?.image ?: "",
            contentDescription = "",
            loading = placeholder(R.mipmap.ic_launcher_round), // Image de chargement
            // Image d'échec. Permet également de voir l'emplacement de l'image dans la Preview
            failure = placeholder(R.mipmap.ic_launcher),
            contentScale = ContentScale.Fit,
            //même autres champs qu'une Image classique
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        )

        Button(
            onClick = {
                viewModel.loadData(foodId)
            },
            contentPadding = ButtonDefaults.ButtonWithIconContentPadding,
            modifier = Modifier.align(CenterHorizontally)
        ) {
            Icon(
                Icons.Filled.Search,
                contentDescription = "Localized description",
                modifier = Modifier.size(ButtonDefaults.IconSize)
            )
            Spacer(Modifier.size(ButtonDefaults.IconSpacing))
            Text("Load")
        }
    }
}


class MexicanFoodViewModel : ViewModel() {
    var mexicanFood by mutableStateOf<MexicanFoodBean?>(null)
        private set

    //Traitement en cours
    var runInProgress by mutableStateOf(false)

    var error by mutableStateOf("")



    fun loadData(foodId: String) {//Simulation de chargement de donnée

        runInProgress = true
        viewModelScope.launch(Dispatchers.Default) {
            try {
                mexicanFood = MexicanFoodAPI.loadMexicanFood(foodId)
            }
            catch(e:Exception){
                e.printStackTrace()
                error = e.message ?: "une erreur est survenue"
            }
            runInProgress = false
        }
    }
}