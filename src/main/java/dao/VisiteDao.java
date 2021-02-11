package dao;

import modeles.Cache;
import modeles.User;
import modeles.Visite;

import java.util.Collection;

public interface VisiteDao extends Dao<Visite> {
    public Collection<Visite> getVisitesByUser(User user);

    public Collection<Visite> getVisitesByCache(Cache cache);
}
