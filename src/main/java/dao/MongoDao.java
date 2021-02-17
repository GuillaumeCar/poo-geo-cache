package dao;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import configuration.GeneralConfiguration;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import java.util.Collection;

public abstract class MongoDao<T> implements Dao<T> {

    protected MongoClient mongoClient;
    protected MongoDatabase database;
    protected Class<T> classEntity;

    public MongoDao(Class<T> entity) {
        this.classEntity = entity;
    }

    public void openSession() {
        mongoClient = new MongoClient(GeneralConfiguration.mongoDatabaseHost, GeneralConfiguration.mongoDatabasePort);
        database = mongoClient.getDatabase(GeneralConfiguration.mongoDatabaseName);
    }

    public void closeSession() {
        mongoClient.close();
    }

    public boolean create(T entity) {
        try {
            Morphia morphia = new Morphia(); // on créé une instance de morphia
            morphia.map(entity.getClass()); // on mappe cette instance a une classe
            Datastore datastore = morphia.createDatastore(mongoClient, GeneralConfiguration.mongoDatabaseName); // on créé un datastore
            datastore.save(entity); // on sauvegarde l'entité en passant par le datastore
            return true;
        } catch (Exception E) {
            System.out.println(E.toString());
            return false;
        }
    }

    public boolean update(T entity) {
        return create(entity);
    }

    public boolean delete(T entity) {
        try {
            Morphia morphia = new Morphia();
            morphia.map(entity.getClass());
            Datastore datastore = morphia.createDatastore(mongoClient, GeneralConfiguration.mongoDatabaseName);
            datastore.delete(entity);
            return true;
        } catch (Exception E) {
            System.out.println(E.toString());
            return false;
        }
    }

    public T find(String id) {
        Morphia morphia = new Morphia();
        Datastore datastore = morphia.createDatastore(mongoClient, GeneralConfiguration.mongoDatabaseName);
        return datastore.get(classEntity, id);
    }

    public Collection<T> findAll() {
        Morphia morphia = new Morphia();
        Datastore datastore = morphia.createDatastore(mongoClient, GeneralConfiguration.mongoDatabaseName);
        return datastore.find(classEntity).asList();
    }

    public boolean deleteAll() {
        try {
            Morphia morphia = new Morphia();
            Datastore datastore = morphia.createDatastore(mongoClient, GeneralConfiguration.mongoDatabaseName);
            datastore.delete(datastore.createQuery(classEntity));
            return true;
        } catch (Exception E) {
            System.out.println(E.toString());
            return false;
        }
    }
}
