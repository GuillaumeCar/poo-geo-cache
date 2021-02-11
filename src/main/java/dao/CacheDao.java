package dao;

import modeles.Cache;
import modeles.Lieu;
import modeles.Type;
import modeles.User;

import java.util.Collection;

public interface CacheDao extends Dao<Cache> {
    public Collection<Cache> getCachesByUser(User user);

    public Collection<Cache> getCachesByType(Type type);

    public Collection<Cache> getCachesByLieu(Lieu lieu);
}
