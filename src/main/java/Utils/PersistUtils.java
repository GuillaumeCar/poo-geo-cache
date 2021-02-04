package Utils;

import org.hibernate.HibernateException;
import javax.persistence.EntityManager;

public class PersistUtils {

    private static EntityManager _em;

    public static void setEntityManager(EntityManager em){
        _em = em;
    }

    public static <T> void persistEntity(T entity) {
        try {
            _em.getTransaction().begin();
            _em.persist(entity);
            _em.getTransaction().commit();
            System.out.println("Entity " + entity.getClass().getName() + " persisted successfully");
        } catch (HibernateException e) {
            e.printStackTrace();
            _em.getTransaction().rollback();
        }
    }
    public static Object getEntity(Class entityClass, int id) {
        return _em.find(entityClass, id);
    }

    public static boolean deleteEntity(Class entityClass, int id) {
        Object entity = getEntity(entityClass, id);
        try {
            _em.getTransaction().begin();
            _em.remove(entity);
            _em.getTransaction().commit();
            System.out.println("Entity " + entity.getClass().getName() + " removed successfully");
            return true;
        } catch (HibernateException e) {
            e.printStackTrace();
            _em.getTransaction().rollback();
        }
        return false;
    }
}