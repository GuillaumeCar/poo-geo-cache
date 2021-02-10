package dao;

import modeles.Lieu;

import java.util.Collection;

public class JpaLieuDao extends JpaDao<Lieu> implements LieuDao {

    private static JpaLieuDao instance;

    public JpaLieuDao() {
        super(Lieu.class);
    }

    /**
     * Retourne une instance de JpaLieuDao.
     * @return JpaLieuDao
     */
    public static JpaLieuDao getInstance() {
        if (instance == null) {
            instance = new JpaLieuDao();
        }
        return instance;
    }
}
