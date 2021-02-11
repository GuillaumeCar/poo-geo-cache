package Test;

import com.github.javafaker.Faker;
import dao.*;
import modeles.*;

import java.util.Locale;
import java.util.UUID;

public class Test3 {

    public static void main(String[] args) {
//        createUser();
//        createVisite();
//        findLieu();
//        deleteLieu();
//        updateUser();
        deleteAllCachesAndUser();
    }


    private static void createUser() {
        MongoUserDao mongoUserDao = MongoUserDao.getInstance();
        try {
            Faker faker = new Faker(new Locale("fr"));
            mongoUserDao.openSession();
            User user = new User(
                    UUID.randomUUID().toString(),
                    faker.gameOfThrones().dragon(),
                    faker.backToTheFuture().quote()
            );
            mongoUserDao.create(user);
        }
        finally {
            mongoUserDao.closeSession();
        }
    }

    private static void createLieu() {
        MongoLieuDao mongoLieuDao =  MongoLieuDao.getInstance();
        try {
            mongoLieuDao.openSession();
            Faker faker = new Faker(new Locale("fr"));
            Lieu lieu = new Lieu(
                    UUID.randomUUID().toString(),
                    faker.gameOfThrones().city()
            );
            if (mongoLieuDao.create(lieu)) {
                System.out.println("Lieu created");
            }
        }
        finally {
            mongoLieuDao.closeSession();
        }
    }

    private static void createVisite() {
        MongoVisiteDao mongoVisiteDao = MongoVisiteDao.getInstance();
        MongoUserDao mongoUserDao = MongoUserDao.getInstance();
        MongoCacheDao mongoCacheDao = MongoCacheDao.getInstance();
        MongoTypeDao mongoTypeDao = MongoTypeDao.getInstance();
        MongoLieuDao mongoLieuDao = MongoLieuDao.getInstance();
        try {
            mongoVisiteDao.openSession();
            mongoUserDao.openSession();
            mongoCacheDao.openSession();
            mongoTypeDao.openSession();
            mongoLieuDao.openSession();

            Faker faker = new Faker(new Locale("fr"));

            User user = new User(UUID.randomUUID().toString(), faker.gameOfThrones().dragon(), faker.backToTheFuture().quote());
            if(mongoUserDao.create(user)) {
                System.out.println("User created");
            }

            Type type = new Type(UUID.randomUUID().toString(), faker.witcher().school());
            if(mongoTypeDao.create(type)) {
                System.out.println("Type created");
            }

            Lieu lieu = new Lieu(UUID.randomUUID().toString(), faker.witcher().school());
            if(mongoLieuDao.create(lieu)) {
                System.out.println("Lieu created");
            }

            Cache cache = new Cache(
                    UUID.randomUUID().toString(),
                    faker.address().latitude() + '/' + faker.address().longitude(),
                    "ok",
                    faker.overwatch().hero(),
                    type,
                    lieu,
                    user
            );
            if(mongoCacheDao.create(cache)) {
                System.out.println("Cache created");
            }

            cache = new Cache(
                    UUID.randomUUID().toString(),
                    faker.address().latitude() + '/' + faker.address().longitude(),
                    "ok",
                    faker.overwatch().hero(),
                    type,
                    lieu,
                    user
            );
            if(mongoCacheDao.create(cache)) {
                System.out.println("Cache created");
            }

            Visite visite = new Visite(
                    UUID.randomUUID().toString(),
                    faker.animal().name(),
                    faker.date().birthday(),
                    faker.backToTheFuture().quote(),
                    true,
                    cache,
                    user
            );
            if(mongoVisiteDao.create(visite)) {
                System.out.println("visite created");
            }
            System.out.println(visite.toString());
        }
        finally {
            mongoVisiteDao.closeSession();
            mongoUserDao.closeSession();
            mongoCacheDao.closeSession();
            mongoTypeDao.closeSession();
            mongoLieuDao.closeSession();
        }
    }

    private static void findLieu() {
        MongoLieuDao mongoLieuDao = MongoLieuDao.getInstance();
        try {
            mongoLieuDao.openSession();
            Lieu lieu = mongoLieuDao.find("b0b6e6f7-f095-4a58-b2de-e9091e922748");
            System.out.println(lieu);
        } catch(Exception e) {
            System.out.println(e.toString());
        } finally {
            mongoLieuDao.closeSession();
        }
    }

    private static void deleteLieu() {
        MongoLieuDao mongoLieuDao = MongoLieuDao.getInstance();
        try {
            mongoLieuDao.openSession();
            Lieu lieu = mongoLieuDao.find("b0b6e6f7-f095-4a58-b2de-e9091e922748");
            mongoLieuDao.delete(lieu);
        } catch(Exception e) {
            System.out.println(e.toString());
        } finally {
            mongoLieuDao.closeSession();
        }
    }

    private static void updateUser() {
        MongoUserDao mongoUserDao = MongoUserDao.getInstance();
        try {
            Faker faker = new Faker(new Locale("fr"));
            mongoUserDao.openSession();
            User user = new User(
                    UUID.randomUUID().toString(),
                    faker.gameOfThrones().dragon(),
                    faker.backToTheFuture().quote()
            );
            mongoUserDao.create(user);
            System.out.println(user.getPseudo());
            user.setPseudo("toto");
            mongoUserDao.update(user);
            System.out.println(user.getPseudo());
        }
        finally {
            mongoUserDao.closeSession();
        }
    }

    private static void deleteAllCachesAndUser() {
        MongoCacheDao mongoCacheDao = MongoCacheDao.getInstance();
        MongoUserDao mongoUserDao = MongoUserDao.getInstance();
        try {
            mongoUserDao.openSession();
            mongoCacheDao.openSession();
//            mongoCacheDao.deleteAll();
            mongoUserDao.deleteAll();
        } catch(Exception e) {
            System.out.println(e.toString());
        } finally {
            mongoCacheDao.closeSession();
            mongoUserDao.closeSession();
        }
    }
}
