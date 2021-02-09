package dao;

import modeles.Cache;

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



}
