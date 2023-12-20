package com.example.a2023_11_cdan_android.exo

import com.google.gson.Gson
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody


val MEDIA_TYPE_JSON = "application/json; charset=utf-8".toMediaType()


fun main() {

    MyAPI.saveMessage(MessageBean("Tata", "Coucou"))

    println(MyAPI.allMessages().joinToString("\n") { "${it.pseudo} : ${it.message}" })
}

object MyAPI {

    const val URL_SERVER = "http://localhost:8080"

    private val client = OkHttpClient()
    private val gson = Gson()

    /* -------------------------------- */
    // TchatAPI
    /* -------------------------------- */
    fun saveMessage(messageBean: MessageBean) {
        val jsonTosend = gson.toJson(messageBean)
        sendPost("$URL_SERVER/tchat/saveMessage", jsonTosend)
    }

    fun allMessages(): List<MessageBean> {
        val json = sendGet("$URL_SERVER/tchat/allMessages")

        return gson.fromJson(json, Array<MessageBean>::class.java).toList()
    }

    /* -------------------------------- */
    // Exo
    /* -------------------------------- */
    fun createStudent(name: String, note: Int): StudentBean {
        var json: String = sendGet("$URL_SERVER/createStudent?nom=$name&note=$note")

        val res: StudentBean = gson.fromJson(json, StudentBean::class.java)
        return res

    }

    fun getStudent(): StudentBean {
        var json: String = sendGet("$URL_SERVER/getStudent")

        val res: StudentBean = gson.fromJson(json, StudentBean::class.java)
        return res

    }

    fun boulangerie(nbCroissant: Int = 0, nbSandwitch: Int = 0): String {
        var res = sendGet("$URL_SERVER/boulangerie?nbCroissant=$nbCroissant&nbSandwitch=$nbSandwitch")
        return res
    }


    fun max(a: Int, b: Int): String {

        var res = sendGet("$URL_SERVER/max?p1=$a&p2=$b")

        return res
    }

    fun increment(studentBean: StudentBean): StudentBean {
        val jsonToSend = gson.toJson(studentBean)
        val jsonReceive = sendPost("$URL_SERVER/increment", jsonToSend)

        return gson.fromJson(jsonReceive, StudentBean::class.java)
    }

    /* -------------------------------- */
    // Méthode d'envoie
    /* -------------------------------- */
    fun sendGet(url: String): String {
        println("url : $url")
        //Création de la requête
        val request = Request.Builder().url(url).build()
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

    fun sendPost(url: String, json: String): String {
        println("url : $url")

        //Corps de la requête
        val body = json.toRequestBody(MEDIA_TYPE_JSON)

        //Création de la requête
        val request = Request.Builder().url(url).post(body).build()

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

//    fun sendPost(url: String, instance: Any): String {
//        println("url : $url")
//
//        //Convertir en JSON
//        val json = Gson().toJson(instance)
//        //Corps de la requête
//        val body = json.toRequestBody(MEDIA_TYPE_JSON)
//
//        //Création de la requête
//        val request = Request.Builder().url(url).post(body).build()
//
//        //Execution de la requête
//        return client.newCall(request).execute().use {
//            //Analyse du code retour
//            if (!it.isSuccessful) {
//                throw Exception("Réponse du serveur incorrect :${it.code}")
//            }
//            //Résultat de la requête
//            it.body.string()
//        }
//    }
}

data class StudentBean(var name: String = "", var note: Int = 0)