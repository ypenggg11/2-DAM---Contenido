package PGL.Actividad1Kotlin

import java.util.*

//Apartado 4
fun main() {
    var rep = true

    while (rep) {
        val island = orientalIslandsWithDiscount()

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

fun orientalIslandsWithDiscount(): String {
    val islands = listOf("La Palma","El Hierro","La Gomera","Tenerife","Gran Canaria","Fuerteventura","Lanzarote","La Graciosa")
    //Extrae las islas que empiecen por la letra L
    val discounts = islands.filter { it[0]=='L' }

    return when (val islandNum = Random().nextInt(8)) {
        in 0..3 -> "No quedan plazas"
        else -> {
            for(island in discounts) {
                if (islands[islandNum]==island)  {
                println("\nTienes un descuento del 10% en ${islands[islandNum]}!");
                }
            }
            islands[islandNum]
        }
    }
}