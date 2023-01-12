import model.entity.Departamentos;
import model.entity.Empleados;
import model.orm.JPA_Manager;

import java.math.BigInteger;

public class Main {
    private static JPA_Manager jpaManager;

    public static void main(String[] args) {

        // Custom class
        jpaManager = new JPA_Manager();

        // Insertar datos
        System.out.println("*Insertando datos...");
        insertData();

        // Obtener entidad por ID
        System.out.println();
        System.out.println("*Recuperando Empleado 551...");
        System.out.println((jpaManager.getEntityById(Empleados.class, 551)).toString());

        // Obtener todos los registros de una entidad
        System.out.println();
        System.out.println("*Recuperando TODOS los empleados...");
        jpaManager.getFullEntityList(Empleados.class).forEach(
                it -> System.out.println(it.toString())
        );

        // Eliminar registro por id de una entidad
        System.out.println();
        System.out.println("*Eliminando empleado 553...");
        JPA_Manager.printConfirmationMessage(
                jpaManager.removeEntityById(Empleados.class, 553),
                JPA_Manager.Actions.DELETE
        );

        // Actualizar entidad
        System.out.println();
        System.out.println("*Actualizando Empleado 552...");
        Empleados empleado = ((Empleados) jpaManager.getEntityById(Empleados.class, 552));
        empleado.setSalarioEmp(empleado.getSalarioEmp()+500);

        JPA_Manager.printConfirmationMessage(
                jpaManager.updateEntity(empleado),
                JPA_Manager.Actions.UPDATE
        );

        jpaManager.close();
    }

    private static void insertData() {

        JPA_Manager.printConfirmationMessage(
                jpaManager.insert(new Departamentos(
                        BigInteger.valueOf(55),
                        "Innovación",
                        "Agüimes"
                )),
                JPA_Manager.Actions.INSERT
        );

        JPA_Manager.printConfirmationMessage(
                jpaManager.insert(new Empleados(
                        BigInteger.valueOf(551),
                        "Peng",
                        1200.0,
                        BigInteger.valueOf(55)
                )),
                JPA_Manager.Actions.INSERT
        );

        JPA_Manager.printConfirmationMessage(
                jpaManager.insert(new Empleados(
                        BigInteger.valueOf(552),
                        "Jimmy",
                        1100.0,
                        BigInteger.valueOf(55)
                )),
                JPA_Manager.Actions.INSERT
        );

        JPA_Manager.printConfirmationMessage(
                jpaManager.insert(new Empleados(
                        BigInteger.valueOf(553),
                        "Nicolas",
                        1150.0,
                        BigInteger.valueOf(55)
                )),
                JPA_Manager.Actions.INSERT
        );
    }
}
