package dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.persistence.EntityManager;

public abstract class JpaDao<T> implements Dao<T> {

    private SessionFactory sessionFactory;
    private Session session;

    protected static EntityManager _em;

    public JpaDao() {
        try {
            Configuration configuration = new Configuration();
            configuration.configure();

            sessionFactory = configuration.buildSessionFactory();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public void openSession() {
        session = sessionFactory.openSession();
        _em = session.getEntityManagerFactory().createEntityManager();
    }

    public void closeSession() {
        if (null != session) {
            session.close();
        }
    }

    public boolean create(T entity) {
        try {
            _em.getTransaction().begin();
            _em.persist(entity);
            _em.getTransaction().commit();
            System.out.println("Entity " + entity.getClass().getName() + " persisted successfully");
            return true;
        } catch (HibernateException e) {
            e.printStackTrace();
            _em.getTransaction().rollback();
        }
        return false;
    }

}
