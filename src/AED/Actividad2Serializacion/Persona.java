package AED.Actividad2Serializacion;

import java.io.Serializable;
import java.text.DecimalFormat;

//Para que permita la escritura de este objeto, tiene q implementar la interfaz Serializable
public class Persona implements Serializable {

    private DecimalFormat decimalFormat;
    private String nombre;
    private String DNI;
    private Integer edad;
    private Double salario;

    public Persona(String nombre, String DNI, Integer edad, Double salario) {
       decimalFormat = new DecimalFormat("#0.00");

        this.nombre = nombre;
        this.DNI = DNI;
        this.edad = edad;
        this.salario = salario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDNI() {
        return DNI;
    }

    public void setDNI(String DNI) {
        this.DNI = DNI;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public Double getSalario() {
        return salario;
    }

    public void setSalario(Double salario) {
        this.salario = salario;
    }

    @Override
    public String toString() {
        return "||----------||Persona||----------||\n" +
                "Nombre: " + nombre + '\n' +
                "DNI: " + DNI + '\n' +
                "Edad: " + edad +'\n'+
                "Salario: " + decimalFormat.format(salario) +
                "€\n";
    }
}
