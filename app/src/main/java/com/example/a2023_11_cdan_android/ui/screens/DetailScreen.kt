package com.example.a2023_11_cdan_android.ui.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.placeholder
import com.example.a2023_11_cdan_android.R
import com.example.a2023_11_cdan_android.exo.pictureList
import com.example.a2023_11_cdan_android.ui.theme._2023_11_cdan_androidTheme


@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun DetailScreenPreview() {
    _2023_11_cdan_androidTheme {
        Surface(modifier = Modifier.fillMaxWidth(), color = Color.LightGray) {
            DetailScreen(position = 0)
        }
    }
}

//Composable représentant l'ensemble de l'écran
@OptIn(ExperimentalFoundationApi::class, ExperimentalGlideComposeApi::class)
@Composable
fun DetailScreen(modifier: Modifier = Modifier, position: Int, navHostController: NavHostController? = null) {

    val pictureData = pictureList.getOrNull(position)

    Column(modifier) {
        Text(
            text = pictureData?.text ?: "Position inconnue $position",
            textAlign = TextAlign.Center,
            modifier = modifier
                .fillMaxWidth()
        )

        if (pictureData != null) {
            GlideImage(
                model = pictureData.url,
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
        }

        Button(
            modifier = Modifier.align(CenterHorizontally),
            onClick = { navHostController?.popBackStack() },
            contentPadding = ButtonDefaults.ButtonWithIconContentPadding
        ) {
            Icon(
                Icons.Filled.ArrowBack,
                contentDescription = "Localized description",
                modifier = Modifier.size(ButtonDefaults.IconSize)
            )
            Spacer(Modifier.size(ButtonDefaults.IconSpacing))
            Text(stringResource(id = R.string.back))
        }

    }
}
