package dao;

import modeles.Type;

public class JpaTypeDao extends JpaDao<Type> implements TypeDao {

    private static JpaTypeDao instance;

    public JpaTypeDao() {
        super(Type.class);
    }

    /**
     * Retourne une instance de JpaTypeDao.
     * @return JpaTypeDao
     */
    public static JpaTypeDao getInstance() {
        if (instance == null) {
            instance = new JpaTypeDao();
        }
        return instance;
    }



}
