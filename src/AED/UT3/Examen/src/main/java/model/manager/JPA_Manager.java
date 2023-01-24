package model.manager;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JPA_Manager {

    /* Possible actions to use in printConfirmationMessage() method */
    public enum Actions {
        INSERT("INSERT"),
        DELETE("DELETE"),
        UPDATE("UPDATE"),
        FIND("FIND");
        Actions(String actionName){}
    }

    /* 'persistence-unit' tag name in persistence.xml  */
    private final String PERSISTENCE_UNIT_NAME;
    private EntityManagerFactory emf;
    private EntityManager em;

    public JPA_Manager(String persistenceUnitName) {
        /* Prevent showing red warning messages in terminal */
        Logger.getLogger("org.hibernate").setLevel(Level.SEVERE);
        this.PERSISTENCE_UNIT_NAME = persistenceUnitName;

        try {
            emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
            em = emf.createEntityManager();
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
    }

    /* Insert the object passed as parameter and return true if it was successful */
    public boolean insert(Object entity) {
        boolean inserted;

        /* When using EntityManager actions, it should be between a begin()
           and commit() method from the EntityTransaction class */
        em.getTransaction().begin();

        try {

            /* Inserts only if the object does not exit in out database */
            if (!(em.createQuery("from " + entity.getClass().getName()).getResultList().contains(entity))) {

                /* Insert method */
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

    /* Return the full list of object from the class passed as parameter */
    public <T> List<T> getFullEntityList(Class<T> entityClass) throws RuntimeException {
        return em.createQuery("from " + entityClass.getName(), entityClass).getResultList();
    }

    /* Return the object from the class with certain id passed as parameter */
    public <T, E> Object getEntityById(Class<T> entityClass, E entityId) throws RuntimeException {
        return em.find(entityClass, entityId);
    }

    /* Remove an entity by his id, and return true if it was successfully removed */
    public <T, E> boolean removeEntityById(Class<T> entityClass, E entityId) {
        boolean removed;

        em.getTransaction().begin();

        try {

            /* Firstly finds it, and after delete the object */
            em.remove(em.find(entityClass, entityId));

            removed = true;
        } catch (RuntimeException ignored) {
            removed = false;
        }

        em.getTransaction().commit();

        return removed;
    }

    /* Overwrite the old entity with a new one passed as parameter, and returns true if the update
    *  was successful. You need to firstly modify the object by yourself (using setters with an existing entity) */
    public boolean updateEntity(Object newEntity) {
        boolean updated;

        em.getTransaction().begin();

        try {

            /* Search the entity where the id it's the same, and overwrites it */
            em.merge(newEntity);

            updated = true;
        }catch (RuntimeException e) {
            updated = false;
        }

        em.getTransaction().commit();

        return updated;
    }

    /* Return a formatted query passed as parameter, and specifies the returning class (HQL) */
    public <T> Query getQuery(Class<T> entityClass, String query) {
        return em.createQuery(query, entityClass);
    }

    /* Print a message depending on the action and a boolean parameter
       (The boolean can be obtained from this class methods, those that returns boolean) */
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

    /* Close, just close, the method name says that by itself */
    public void close() {
        em.close();
        emf.close();
    }
}
