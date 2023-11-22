package com.example.a2023_11_cdan_android.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.placeholder
import com.example.a2023_11_cdan_android.R
import com.example.a2023_11_cdan_android.exo.PictureData
import com.example.a2023_11_cdan_android.exo.pictureList
import com.example.a2023_11_cdan_android.ui.theme._2023_11_cdan_androidTheme

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PictureRowItemPreview() {
    //nom visible dans MainActivity en dessous de setContent{
    _2023_11_cdan_androidTheme {
        Column {
            PictureRowItem(pictureList[0])
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun PictureRowItem(data: PictureData, modifier: Modifier = Modifier) {
    Row(modifier = modifier.height(100.dp)) {

        GlideImage(
            model = data.url,
            contentDescription = "coucou",
            loading = placeholder(R.mipmap.ic_launcher_round), // Image de chargement
            // Image d'échec. Permet également de voir l'emplacement de l'image dans la Preview
            failure = placeholder(R.mipmap.ic_launcher),
            contentScale = ContentScale.Fit,
            //même autres champs qu'une Image classique
            modifier = Modifier
                .heightIn(max = 100.dp) //Sans hauteur il prendra tous l'écran
                .widthIn(max = 100.dp)
        )

        Column(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = data.text,
                color = Color.Blue,
                fontSize = 20.sp

            )
            Text(
                text = data.longText.take(15),
                fontSize = 14.sp,

                )
        }
    }
}