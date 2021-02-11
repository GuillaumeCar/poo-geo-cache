package dao;

import modeles.Lieu;

public class MongoLieuDao extends MongoDao<Lieu> implements LieuDao {

    private static MongoLieuDao instance;

    public MongoLieuDao() {
        super(Lieu.class);
    }

    /**
     * Retourne une instance de JpaLieuDao.
     * @return JpaLieuDao
     */
    public static MongoLieuDao getInstance() {
        if (instance == null) {
            instance = new MongoLieuDao();
        }
        return instance;
    }
}
