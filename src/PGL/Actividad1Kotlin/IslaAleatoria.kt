package Actividad1Kotlin

import java.util.*

//Apartado 2
fun main() {
    print("Me voy de vacaciones a ${island()}")
}

fun island(): String {
    val islands = listOf("La Palma","El Hierro","La Gomera","Tenerife","Gran Canaria","Fuerteventura","Lanzarote","La Graciosa")
    return (islands[Random().nextInt(8)])
}