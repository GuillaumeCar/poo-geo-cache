package dao;

import modeles.Cache;
import modeles.Lieu;
import modeles.Type;
import modeles.User;

import java.util.Collection;

public class MongoCacheDao extends MongoDao<Cache> implements CacheDao {

    private static MongoCacheDao instance;

    public MongoCacheDao() {
        super(Cache.class);
    }

    /**
     * Retourne une instance de JpaCacheDao.
     * @return JpaCacheDao
     */
    public static MongoCacheDao getInstance() {
        if (instance == null) {
            instance = new MongoCacheDao();
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
