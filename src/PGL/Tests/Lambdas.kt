package PGL.Tests

//Lambda

fun main() {
    val square = {number: Int -> number * number}
    println(square(4))
}

//Lambda Como Argumento

//Existe la función CharSequence.count(), a la cual podemos pasarle un lambda
//que represente el predicado propuesto en nuestro problema.
//El predicado char -> char == 'e' se podría leer como «al parámetro char corresponde su resultado de igualdad con ‘e‘»
fun main2() {
    val eCount = "develou.com".count({ char:Char -> char == 'e' })
    print("Total 'e': $eCount")
}

//Omitir Paréntesis De Función

fun main3() {
    //Si la expresión lambda es el último argumento en la función,
    // puedes escribir las llaves por fuera del paréntesis
    val eCount = "develou.com".count() { char:Char -> char == 'e' }

    //Y si los paréntesis están vacíos, entonces puedes omitirlos completamente:
    val eCount2 = "develou.com".count { char:Char -> char == 'e' }

    print("Total 'e': $eCount")
}

//El Identificador it

//Cuando tu lambda usa un único argumento y no piensas cambiar su nombre por cuestiones de legibilidad,
// puedes usar el identificador it.
//Esta variable se deduce implícitamente con el tipo inferido por el compilador
// y puedes referirte a ella como tu parámetro.
fun main4() {
    val eCount = "develou.com".count { it == 'e' }
    print("Total 'e': $eCount")
}

//Lambdas Con Múltiples Líneas

fun main5() {
    val eCount = "develou.com".count {
        println("Carácter $it")
        it == 'e'
    }
    print("Total 'e': $eCount")
}






