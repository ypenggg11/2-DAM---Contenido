package Postgre;

import Postgre.model.orm.JPA_Manager;

import java.util.Scanner;

public class Main {

    private static JPA_Manager jpaManager;
    private static Scanner scanner;
    public static void main(String[] args) {

        //TODO Terminar actividad

        scanner = new Scanner(System.in);
        jpaManager = new JPA_Manager("postgres_hibernate");

        // Consulta de todos los empleados que trabajan en dos departamentos (con
        //parámetros solicitados al usuario y que se pasan a la query) y que cobran más de 1500€


        jpaManager.close();
    }
}
