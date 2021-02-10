package dao;

import modeles.Cache;
import modeles.User;
import modeles.Visite;

import java.util.Collection;

public class JpaVisiteDao extends JpaDao<Visite> implements VisiteDao {

    private static JpaVisiteDao instance;

    public JpaVisiteDao() {
        super(Visite.class);
    }

    /**
     * Retourne une instance de JpaVisiteDao.
     * @return JpaVisiteDao
     */
    public static JpaVisiteDao getInstance() {
        if (instance == null) {
            instance = new JpaVisiteDao();
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
