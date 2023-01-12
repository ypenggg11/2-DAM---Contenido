package model.orm;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JPA_Manager {

    public enum Actions {
        INSERT("INSERT"),
        DELETE("DELETE"),
        UPDATE("UPDATE"),
        FIND("FIND");
        Actions(String actionName){}
    }
    private final String PERSISTENCE_UNIT_NAME = "oracle_hibernate";
    private EntityManagerFactory emf;
    private EntityManager em;

    public JPA_Manager() {
        Logger.getLogger("org.hibernate").setLevel(Level.SEVERE);

        try {
            emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
            em = emf.createEntityManager();
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
    }

    public boolean insert(Object entity) {
        boolean inserted;

        em.getTransaction().begin();

        try {
            if (!(em.createQuery("from " + entity.getClass().getName()).getResultList().contains(entity))) {
                em.persist(entity);
                inserted = true;
            } else {
                inserted = false;
            }
        } catch (RuntimeException e) {
            inserted = false;
        }

        em.getTransaction().commit();

        return inserted;
    }

    public <T> List<T> getFullEntityList(Class<T> entityClass) throws RuntimeException {
        return em.createQuery("from " + entityClass.getName(), entityClass).getResultList();
    }

    public <T, E> Object getEntityById(Class<T> entityClass, E entityId) throws RuntimeException {
        return em.find(entityClass, entityId);
    }

    public <T, E> boolean removeEntityById(Class<T> entityClass, E entityId) {
        boolean removed;

        em.getTransaction().begin();

        try {
            em.remove(em.find(entityClass, entityId));
            removed = true;
        } catch (RuntimeException ignored) {
            removed = false;
        }

        em.getTransaction().commit();

        return removed;
    }

    public boolean updateEntity(Object newEntity) {
        boolean updated;

        em.getTransaction().begin();

        try {
            em.merge(newEntity);
            updated = true;
        }catch (RuntimeException e) {
            updated = false;
        }

        em.getTransaction().commit();

        return updated;
    }

    public static void printConfirmationMessage(boolean b, Actions action) {
        if (b) {
            System.out.println(action.name() + " successful!");
        } else {
            if (action == Actions.INSERT) {
                System.out.println("Duplicated entry!");
            } else {
                System.out.println(action.name() + " failed!");
            }
        }
    }

    public void close() {
        em.close();
        emf.close();
    }
}
