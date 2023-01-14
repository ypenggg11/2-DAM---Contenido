import jakarta.persistence.*;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();

            /*EmpleadosEntity empleados = new EmpleadosEntity();
            empleados.setId(BigInteger.valueOf(4));
            empleados.setIdEmp(BigInteger.valueOf(4));
            empleados.setNombreEmp("Hibernate");
            empleados.setCargoEmp("Framework");
            empleados.setFechaIngreso(new Date(2022-1900,11,15));
            empleados.setSalario(BigInteger.valueOf(1000));
            empleados.setComision(BigInteger.valueOf(0));
            empleados.setIdDptEmp(BigInteger.valueOf(2));*/

            //Añade a la base de datos
            //(Puede dar error "PersistentObjectException", ya que no deja cambiar el valor del id (id_dpt))
            //(Solución: quitar de cada clase Entity -> @GeneratedValue(strategy = GenerationType.IDENTITY) del id)
            /*entityManager.persist(empleados);*/

            Query fromDepartamentosEntity = entityManager.createQuery("from DepartamentosEntity");
                
            fromDepartamentosEntity.getResultList().forEach(it -> {
                System.out.println(it.toString());
            });

            transaction.commit();
        } finally {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            entityManager.close();
            entityManagerFactory.close();
        }
    }
}
