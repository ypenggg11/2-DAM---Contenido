package Actividad1Kotlin

//Apartado 7
fun main() {
   val empleados = arrayListOf<Empleados>()
   addEmpleados(empleados)

   for (empleado in empleados) {
      println("===========||EMPLEADOS CREADOS||===========")
      println("""
         |Identificador: ${empleado.indentificador}
         |Nombre: ${empleado.nombre}
         |Direccion: ${empleado.direccion}
         |Salario: ${empleado.salario}
      """.trimMargin())
   }

   //Easier forEach method

//   empleados.forEach {
//      println("===========||EMPLEADOS CREADOS||===========")
//      println("""
//         |Identificador: ${it.indentificador}
//         |Nombre: ${it.nombre}
//         |Direccion: ${it.direccion}
//         |Salario: ${it.salario}
//      """.trimMargin())
//   }

}

private fun addEmpleados(empleados: ArrayList<Empleados>) {
   for (i in 0 until 6) {
      println("===============||EMPLEADOS||===============")
      print("Identificador: ")
      val identificador = readLine()!!.toString()

      print("Nombre: ")
      val nombre = readLine()!!.toString()

      print("Direccion: ")
      val direccion = readLine()!!.toString()

      print("Salario: ")
      val salario:Double = try {
         readLine()!!.toDouble()
      }catch (_:NumberFormatException) {
         0.0
      }

      empleados.add(Empleados(identificador, nombre, direccion, salario))
   }
}


