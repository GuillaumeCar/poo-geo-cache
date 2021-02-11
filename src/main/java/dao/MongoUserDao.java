package dao;

import modeles.User;

public class MongoUserDao extends MongoDao<User> implements UserDao {

    private static MongoUserDao instance;

    public MongoUserDao() {
        super(User.class);
    }

    /**
     * Retourne une instance de JpaUserDao.
     * @return JpaUserDao
     */
    public static MongoUserDao getInstance() {
        if (instance == null) {
            instance = new MongoUserDao();
        }
        return instance;
    }
}
