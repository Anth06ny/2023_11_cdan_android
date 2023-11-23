package com.example.a2023_11_cdan_android.ui.screens

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.placeholder
import com.example.a2023_11_cdan_android.R
import com.example.a2023_11_cdan_android.exo.PictureData
import com.example.a2023_11_cdan_android.exo.pictureList
import com.example.a2023_11_cdan_android.ui.Routes
import com.example.a2023_11_cdan_android.ui.theme._2023_11_cdan_androidTheme

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun SearchScreenPreview() {
    _2023_11_cdan_androidTheme {
        Surface(modifier = Modifier.fillMaxWidth(), color = Color.LightGray) {
            SearchScreen()
        }
    }
}

//Composable représentant l'ensemble de l'écran
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SearchScreen(modifier: Modifier = Modifier, navHostController: NavHostController?= null) {

    //Etat
    var searchText by remember { mutableStateOf("") }

    //Données
    var filterList = pictureList.filter { it.text.contains(searchText, true) }

    Column(modifier) {


        SearchBar(
            texte = searchText,
            onValueChange = {
                searchText =it
            }
        )
        Spacer(Modifier.size(8.dp))
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.weight(1f)
        ) {
            items(filterList.size) {
                PictureRowItem(filterList[it],
                    modifier = Modifier.background(Color.White),
                    onPictureClick = {  navHostController?.navigate(Routes.DetailScreen.addParam(it))}
                )
            }
        }

        Row(

            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            Button(
                onClick = {
                          searchText = ""
                },
                contentPadding = ButtonDefaults.ButtonWithIconContentPadding
            ) {
                Icon(
                    Icons.Filled.Clear,
                    contentDescription = "Localized description",
                    modifier = Modifier.size(ButtonDefaults.IconSize)
                )
                Spacer(Modifier.size(ButtonDefaults.IconSpacing))
                Text("Clear Filter")
            }

            Button(
                onClick = { /* Do something! */ },
                contentPadding = ButtonDefaults.ButtonWithIconContentPadding
            ) {
                Icon(
                    Icons.Filled.Send,
                    contentDescription = "Localized description",
                    modifier = Modifier.size(ButtonDefaults.IconSize)
                )
                Spacer(Modifier.size(ButtonDefaults.IconSpacing))
                Text("Load Data")
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(modifier: Modifier = Modifier,texte :String,  onValueChange: (String) -> Unit ) {


    TextField(
        value = texte, //Valeur par défaut
        onValueChange = onValueChange, //Action
        leadingIcon = { //Image d'icone
            Icon(
                imageVector = Icons.Default.Search,
                tint = MaterialTheme.colorScheme.primary,
                contentDescription = null
            )
        },
        label = { Text("Enter text") }, //Texte d'aide qui se déplace

        //Comment le composant doit se placer
        modifier = modifier
            .fillMaxWidth() // Prend toute la largeur
            .heightIn(min = 56.dp) //Hauteur minimum
    )
}

//Composable affichant 1 PictureData
@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun PictureRowItem(data: PictureData, modifier: Modifier = Modifier, onPictureClick: () -> Unit = {  }) {

    //Etat
    var fullText by remember {mutableStateOf(false) }

    //Donnée
    val textToShow = if(fullText) data.longText else data.longText.take(15)

    Row(modifier = modifier) {

        GlideImage(
            model = data.url,
            contentDescription = "",
            loading = placeholder(R.mipmap.ic_launcher_round), // Image de chargement
            // Image d'échec. Permet également de voir l'emplacement de l'image dans la Preview
            failure = placeholder(R.mipmap.ic_launcher),
            contentScale = ContentScale.Fit,
            //même autres champs qu'une Image classique
            modifier = Modifier
                .heightIn(max = 100.dp) //Sans hauteur il prendra tous l'écran
                .widthIn(max = 100.dp)
                .clickable(onClick = onPictureClick)

        )

        Column(modifier = Modifier
            .fillMaxWidth()
            .clickable {
                fullText = !fullText
            }
        ) {
            Text(
                text = data.text,
                color = Color.Blue,
                fontSize = 20.sp

            )
            Text(
                text = textToShow,
                fontSize = 14.sp,
                modifier = Modifier.animateContentSize()
            )
        }
    }
}