package dao;

import modeles.Visite;

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
}
