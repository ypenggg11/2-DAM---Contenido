import entity.DepartamentosEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try{
            transaction.begin();

            DepartamentosEntity departamentos = new DepartamentosEntity();
            departamentos.setIdDpt(5);
            departamentos.setNombreDpt("Hibernate");
            departamentos.setLocalidadDpt("Las Palmas");

            //Añade a la base de datos
            //(Puede dar error "PersistentObjectException", ya que no deja cambiar el valor del id (id_dpt))
            //(Solución: quitar de cada clase Entity -> @GeneratedValue(strategy = GenerationType.IDENTITY) del id)
            entityManager.persist(departamentos);

            transaction.commit();
        }finally {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            entityManager.close();
            entityManagerFactory.close();
        }
    }
}
