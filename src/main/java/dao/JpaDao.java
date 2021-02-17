package dao;

import configuration.GeneralConfiguration;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Collection;

public abstract class JpaDao<T> implements Dao<T> {

    private SessionFactory sessionFactory;
    private Session session;

    protected static EntityManager _em;
    protected Class<T> classEntity;

    public JpaDao(Class<T> entity) {
        try {
            Configuration configuration = new Configuration();
            configuration.configure();

            sessionFactory = configuration.buildSessionFactory();
            this.classEntity = entity;
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

    public boolean update(T entity) {
        try {
            _em.getTransaction().begin();
            _em.merge(entity);
            _em.getTransaction().commit();
            System.out.println("Entity " + entity.getClass().getName() + " updated successfully");
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    public boolean delete(T entity) {
        try {
            _em.getTransaction().begin();
            _em.remove(entity);
            _em.getTransaction().commit();
            System.out.println("Entity " + entity.getClass().getName() + " removed successfully");
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    public T find(String id) {
        return _em.find(this.classEntity, id);
    }

    public Collection<T> findAll() {
        CriteriaBuilder cb = _em.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(this.classEntity);
        Root<T> tacks = cq.from(this.classEntity);
        cq.select(tacks);
        return _em.createQuery(cq).getResultList();
    }

    public boolean deleteAll() {
        try {
            _em.getTransaction().begin();
            CriteriaBuilder cb = _em.getCriteriaBuilder();
            // create delete
            CriteriaDelete<T> delete = cb.createCriteriaDelete(classEntity);
            // set the root class
            Root<T> tacks = delete.from(classEntity);
            // perform update
            _em.createQuery(delete).executeUpdate();
            _em.getTransaction().commit();
            return true;
        } catch (Exception E){
            System.out.println(E.toString());
            return false;
        }


    }
}
