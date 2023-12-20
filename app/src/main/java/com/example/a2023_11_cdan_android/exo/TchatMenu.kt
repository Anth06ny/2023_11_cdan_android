package com.example.a2023_11_cdan_android.exo

import kotlin.system.exitProcess

fun main() {

    while (true) {
        println("1- Afficher les 5 derniers messages")
        println("2- Envoyer un message")
        println("3- Quitter")
        print("Que voulez vous faire? : ")
        try {
            try {
                when (readLine()?.toInt()) {
                    1    -> afficher5Messages()
                    2    -> envoyerUnMessage()
                    3    -> exitProcess(0)
                    else -> println("Entrée incorrecte ")
                }
            }
            catch (e: Exception) {
                println("Une erreur c'est produite : " + e.message)
            }
        }
        catch (e: Exception) {
            println("Un chiffre est attendu")
        }
        println("\n===========\n")
    }
}

fun envoyerUnMessage() {
    print("Pseudo : ")
    val pseudo = readLine() ?: throw Exception("Il faut un pseudo")
    print("Message : ")
    val message = readLine() ?: throw Exception("Il faut un pseudo")

    MyAPI.saveMessage(MessageBean(pseudo, message))
    afficher5Messages()
}

fun afficher5Messages() {
    println(MyAPI.allMessages().takeLast(5).joinToString("\n") { "${it.pseudo} : ${it.message}" })
}