package Actividad1Kotlin

import java.util.*

//Apartado 3
fun main() {
    var rep = true

    while (rep) {
        val island = orientalIslands()

        if (island == "No quedan plazas") {
            println(island)
        }else {
            println("Me voy de vacaciones a $island")
            if (island == "La Graciosa") {
                rep = false
            }
        }
    }
}

fun orientalIslands(): String {
    val islands = listOf("La Palma","El Hierro","La Gomera","Tenerife","Gran Canaria","Fuerteventura","Lanzarote","La Graciosa")
    return when (val islandNum = Random().nextInt(8)) {
        in 0..3 -> "No quedan plazas"
        else -> {islands[islandNum]}
    }
}