import model.entity.Departamentos;
import model.entity.Empleados;
import model.jpaManager.JPA_Manager;

import java.math.BigInteger;

public class Main {
    //TODO Apartados 5 y 6
    private static JPA_Manager jpaManager;

    public static void main(String[] args) {

        jpaManager = new JPA_Manager();

        // 1.- & 2.-
        insertData();

        // 3.-
        jpaManager.getFullEntityList(Empleados.class).forEach( it -> {
            if (it.getIdEmp().equals(BigInteger.valueOf(551))) {
                System.out.println(it);
            }
        });


        // 4.-
        jpaManager.getFullEntityList(Empleados.class).forEach(
                it -> System.out.println(it.toString()))
        ;

        jpaManager.close();
    }

    private static void insertData() {
        // 1.-
        Departamentos departamento = new Departamentos();
        departamento.setIdDpt(BigInteger.valueOf(55));
        departamento.setNombreDpt("Innovación");
        departamento.setLocalidadDpt("Agüimes");

        jpaManager.insert(departamento);

        // 2.-
        Empleados empleado1 = new Empleados();
        empleado1.setIdEmp(BigInteger.valueOf(551));
        empleado1.setNombreEmp("Peng");
        empleado1.setSalarioEmp(1200.0);
        empleado1.setIdDptEmp(BigInteger.valueOf(55));

        jpaManager.insert(empleado1);

        Empleados empleado2 = new Empleados();
        empleado2.setIdEmp(BigInteger.valueOf(552));
        empleado2.setNombreEmp("Jimmy");
        empleado2.setSalarioEmp(1000.0);
        empleado2.setIdDptEmp(BigInteger.valueOf(55));

        jpaManager.insert(empleado2);

        Empleados empleado3 = new Empleados();
        empleado3.setIdEmp(BigInteger.valueOf(553));
        empleado3.setNombreEmp("Nicolas");
        empleado3.setSalarioEmp(1100.0);
        empleado3.setIdDptEmp(BigInteger.valueOf(55));

        jpaManager.insert(empleado3);
    }
}
