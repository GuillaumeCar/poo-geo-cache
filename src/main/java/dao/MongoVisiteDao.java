package dao;

import modeles.Cache;
import modeles.User;
import modeles.Visite;

import java.util.Collection;

public class MongoVisiteDao extends MongoDao<Visite> implements VisiteDao {

    private static MongoVisiteDao instance;

    public MongoVisiteDao() {
        super(Visite.class);
    }

    /**
     * Retourne une instance de JpaVisiteDao.
     * @return JpaVisiteDao
     */
    public static MongoVisiteDao getInstance() {
        if (instance == null) {
            instance = new MongoVisiteDao();
        }
        return instance;
    }

    public Collection<Visite> getVisitesByUser(User user) {
        return user.getListeVisite();
    }

    public Collection<Visite> getVisitesByCache(Cache cache) {
        return cache.getListeVisite();
    }
}
