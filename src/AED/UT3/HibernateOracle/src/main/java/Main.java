import model.entity.Departamentos;
import model.entity.Empleados;
import model.jpaManager.JPA_Manager;

import java.math.BigInteger;

public class Main {
    //TODO Apartados 5 y 6
    private static JPA_Manager jpaManager;

    public static void main(String[] args) {

        // Custom class
        jpaManager = new JPA_Manager();

        // Insertar datos
        insertData();

        // Obtener entidad por ID
        System.out.println((jpaManager.getEntityById(Empleados.class, 551)).toString());

        // Obtener todos los registros de una entidad
        jpaManager.getFullEntityList(Empleados.class).forEach(
                it -> System.out.println(it.toString())
        );

        // Eliminar registro por id de una entidad
        JPA_Manager.printConfirmationMessage(
                jpaManager.removeEntityById(Empleados.class, 553),
                JPA_Manager.Actions.DELETE
        );

        // Actualizar entidad
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
