package com.example.a2023_11_cdan_android.exo

import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import okhttp3.OkHttpClient
import okhttp3.Request
import java.lang.reflect.Method

fun main() {
    println(MexicanFoodAPI.loadMexicanFood("4"))
}
object MexicanFoodAPI {

    //Attribut instancié 1 seule fois car c'est un singleton
    //Et uniquement à la 1er utilisation (Lazy Loading)
    private val client = OkHttpClient()
    val gson = Gson()

    fun loadMexicanFood(id: String): MexicanFoodBean {
        //Eventuel contrôle
        //Réaliser la requête.
        val json: String = sendGet("/$id")

        //Parser le JSON avec le bon bean et GSON
        return gson.fromJson(json, MexicanFoodBean::class.java)

    }


    fun sendGet(url: String): String {
        println("url : $url")
        //Création de la requête
        val request = Request.Builder()
            .url("https://the-mexican-food-db.p.rapidapi.com$url")
            .get()
            .addHeader("X-RapidAPI-Key", "93329c7cf9msha136bd696cd1040p10a1dejsnbc52cdb0746e")
            .addHeader("X-RapidAPI-Host", "the-mexican-food-db.p.rapidapi.com")
            .build()
        //Execution de la requête
        return client.newCall(request).execute().use {
            //Analyse du code retour
            if (!it.isSuccessful) {
                throw Exception("Réponse du serveur incorrect :${it.code}")
            }
            //Résultat de la requête
            it.body.string()
        }
    }
}
data class MexicanFoodBean(
    val description: String,
    val difficulty: String,
    val id: String,
    val image: String,
    val ingredients: List<String>,
    val method: List<StepsBean>,
    val portion: String,
    val time: String,
    val title: String
)

data class StepsBean(
    @SerializedName("Step 1")
    val step1: String?,
    @SerializedName("Step 2")
    val step2: String?,
    @SerializedName("Step 3")
    val step3: String?,
    @SerializedName("Step4")
    val step4: String?,
    @SerializedName("Step 5")
    val step5: String?,

)
