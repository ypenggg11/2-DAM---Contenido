package model.jpaManager;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import model.entity.Departamentos;
import model.entity.Empleados;

public class JPA_Manager {

    private final EntityManagerFactory emf;
    private final  EntityManager em;
    public JPA_Manager() {
        emf = Persistence.createEntityManagerFactory("oracle_hibernate");
        em = emf.createEntityManager();
    }

    public void insert(Object obj) {
        em.getTransaction().begin();

        if (!(em.createQuery("from "+obj.getClass().getName()).getResultList().contains(obj))){
            em.persist(obj);
            System.out.println(obj.getClass().getName()+" entry inserted");
        } else {
            System.out.println(obj.getClass().getName()+" duplicated entry");
        }

        em.getTransaction().commit();
    }

    public void close() {
        em.close();
        emf.close();
    }
}
