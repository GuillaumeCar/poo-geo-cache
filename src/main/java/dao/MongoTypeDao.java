package dao;

import modeles.Type;

public class MongoTypeDao extends MongoDao<Type> implements TypeDao {

    private static MongoTypeDao instance;

    public MongoTypeDao() {
        super(Type.class);
    }

    /**
     * Retourne une instance de JpaTypeDao.
     * @return JpaTypeDao
     */
    public static MongoTypeDao getInstance() {
        if (instance == null) {
            instance = new MongoTypeDao();
        }
        return instance;
    }
}
