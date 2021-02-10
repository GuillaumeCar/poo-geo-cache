package dao;

import modeles.*;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Collection;

public class JpaCacheDao extends JpaDao<Cache> implements CacheDao {

    private static JpaCacheDao instance;

    public JpaCacheDao() {
        super(Cache.class);
    }

    /**
     * Retourne une instance de JpaCacheDao.
     * @return JpaCacheDao
     */
    public static JpaCacheDao getInstance() {
        if (instance == null) {
            instance = new JpaCacheDao();
        }
        return instance;
    }

    public Collection<Cache> getCachesByUser(User user) {
        return user.getListeCache();
    }

    public Collection<Cache> getCachesByType(Type type) {
        return type.getListeCache();
    }

    public Collection<Cache> getCachesByLieu(Lieu lieu) {
        return lieu.getListeCache();
    }

//    public Collection<Cache> getCachesByUser(User user) {
//        CriteriaBuilder cb = _em.getCriteriaBuilder();
//        CriteriaQuery<Cache> cq = cb.createQuery(this.classEntity);
//        Root<Cache> caches = cq.from(this.classEntity);
//        cq.where(cb.equal(caches.get("user"), user.getId()));
//        return _em.createQuery(cq).getResultList();
//    }
}
