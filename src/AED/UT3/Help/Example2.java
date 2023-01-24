package Postgre;

import Postgre.model.entity.PostgreEmpleados;
import Postgre.model.orm.JPA_Manager;
import jakarta.persistence.Query;

import java.util.List;
import java.util.Scanner;

public class Main {

    private static JPA_Manager jpaManager;

    private static Scanner scanner;

    /* En la base de datos de Postgre, TODOS los nombres de tablas y campos,
    *  TIENEN que ser minúsculas, y en cada entidad, quitar el 'public' */
    public static void main(String[] args) {
        scanner = new Scanner(System.in);
        jpaManager = new JPA_Manager("postgres_hibernate");

        // Consulta de todos los empleados que trabajan en dos departamentos (con
        //parámetros solicitados al usuario y que se pasan a la query) y que cobran más de 1500€
        List<PostgreEmpleados> list = consulta();
        if (!list.isEmpty()) {
            list.forEach(it -> System.out.println(it.toString()));
        } else {
            System.out.println("No se ha encontrado cierto departamento");
        }

        //Modificar el sueldo de los empleados que pertenecen a los departamentos anteriores e
        //incrementarles el sueldo actual en 500€.
        modificar(list);

        // Eliminar los empleados que trabajan en la localidad de "Agüimes"
        eliminar();

        //Preparar una consulta que te permita visualizar el contenido de las dos tablas
        //relacionadas, con todos sus campos
        jpaManager.getFullEntityList(PostgreEmpleados.class).forEach(System.out::println);

        jpaManager.close();
    }

    private static void eliminar() {
        jpaManager.getFullEntityList(PostgreEmpleados.class).forEach( it -> {
            if (it.getDepartamentosByIdDptEmp().getLocalidadDPt().trim().equals("Agüimes")) {
                JPA_Manager.printConfirmationMessage(
                        jpaManager.removeEntityById(PostgreEmpleados.class,it.getIdEmp()),
                        JPA_Manager.Actions.DELETE
                );
            }
        });
    }

    private static void modificar(List<PostgreEmpleados> list) {
        for (PostgreEmpleados empleado:list) {
            empleado.setSalarioEmp(empleado.getSalarioEmp()+500);
            JPA_Manager.printConfirmationMessage(
                    jpaManager.updateEntity(empleado),
                    JPA_Manager.Actions.UPDATE
            );
        }
    }

    private static List<PostgreEmpleados> consulta() {
        /* Para utilizar .setParameter(), se usa '?1 o ?2...' o con ':id o :algo' */
        String query = "SELECT e FROM PostgreEmpleados e " +
                "INNER JOIN PostgreDepartamentos d " +
                "ON d.idDpt = e.idDptEmp " +
                "WHERE d.nombreDpt IN (?1,?2) " +
                "AND e.salarioEmp > 1500";
        Query q = jpaManager.getQuery(PostgreEmpleados.class, query);

        System.out.print("Nombre del primer departamento: ");
        q.setParameter(1,scanner.nextLine());

        System.out.print("Nombre del segundo departamento: ");
        q.setParameter(2,scanner.nextLine());

        return q.getResultList();
    }
}
