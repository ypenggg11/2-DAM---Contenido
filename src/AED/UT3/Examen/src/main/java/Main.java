import jakarta.persistence.Query;
import model.entity.Alumnos;
import model.manager.JPA_Manager;

import java.util.Scanner;

public class Main {
    private static Scanner scanner;
    private static JPA_Manager jpaManager;
    public static void main(String[] args) {
        scanner = new Scanner(System.in);
        jpaManager = new JPA_Manager("examen");

        /* 3.- Realiza la inserción de al menos 3 registros desde Java */
        System.out.println("\nInsertando...");
        insert();

        /* 5.a.- Obtener todos los datos de los alumnos */
        System.out.println("\nRecuperando...");
        jpaManager.getFullEntityList(Alumnos.class).forEach( alumno ->
                System.out.println(alumno.toString())
        );

        /* 5.b.- Obtener el nombre del alumno con un CIAL concreto que será
        *        pasado por parámetro */
        printAlumnoByCial();

        /* 5.c.- Modificar el teléfono de los alumnos que empiecen por 928
                 y cambiarlos a "922123123" */
        changeTelefono();

        /* 5.d.- Elimina al alumno con un NIF concreto, pasado también por parámetro */
        System.out.println();
        System.out.print("Introduzca el NIF del alumno a eliminar: ");
        deleteAlumnoByNif(scanner.nextLine());

        jpaManager.close();
    }

    private static void deleteAlumnoByNif(String nif) {
        System.out.println();
        JPA_Manager.printConfirmationMessage(
                jpaManager.removeEntityById(Alumnos.class,nif),
                JPA_Manager.Actions.DELETE
        );

        System.out.println("\nRecuperando...");
        jpaManager.getFullEntityList(Alumnos.class).forEach( alumno ->
                System.out.println(alumno.toString())
        );
    }

    private static void changeTelefono() {
        System.out.println("\nModificando...");
        jpaManager.getFullEntityList(Alumnos.class).forEach( alumno -> {
            if (alumno.getTelefono().startsWith("928")) {

                alumno.setTelefono("922123123");

                System.out.println();
                JPA_Manager.printConfirmationMessage(
                        jpaManager.updateEntity(alumno),
                        JPA_Manager.Actions.UPDATE
                );
            }

            System.out.println(alumno);
        });

    }

    private static void printAlumnoByCial() {
        System.out.println("\nRecuperando...");
        String query = "FROM Alumnos a WHERE a.cial LIKE (?1)";
        Query q = jpaManager.getQuery(Alumnos.class, query);

        System.out.print("\nCial del alumno a buscar: ");
        q.setParameter(1,scanner.nextLine());

        Alumnos alumno = (Alumnos) q.getSingleResult();

        System.out.println("\n||===============||Alumno||===============||\n" +
                "Nombre: " + alumno.getNombre() + '\n' +
                "Cial: " + alumno.getCial());
    }

    private static void insert() {
        JPA_Manager.printConfirmationMessage(
                jpaManager.insert(new Alumnos("42300200P","Shakira","999A","600010203")),
                JPA_Manager.Actions.INSERT
        );

        JPA_Manager.printConfirmationMessage(
                jpaManager.insert(new Alumnos("45100200P","Piqué","666Z","928010203")),
                JPA_Manager.Actions.INSERT
        );

        JPA_Manager.printConfirmationMessage(
                jpaManager.insert(new Alumnos("55300200H","Bob Esponja","555L","922010203")),
                JPA_Manager.Actions.INSERT
        );

        JPA_Manager.printConfirmationMessage(
                jpaManager.insert(new Alumnos("45300200T","Calamardo","112A","606010203")),
                JPA_Manager.Actions.INSERT
        );

        JPA_Manager.printConfirmationMessage(
                jpaManager.insert(new Alumnos("54900200V","Patricio","805P","928010203")),
                JPA_Manager.Actions.INSERT
        );
    }
}
