package dao;

import modeles.Cache;
import modeles.User;
import modeles.Visite;

import java.util.Collection;

public class JpaUserDao extends JpaDao<User> implements UserDao {

    private static JpaUserDao instance;

    public JpaUserDao() {
        super(User.class);
    }

    /**
     * Retourne une instance de JpaUserDao.
     * @return JpaUserDao
     */
    public static JpaUserDao getInstance() {
        if (instance == null) {
            instance = new JpaUserDao();
        }
        return instance;
    }
}
