package Test;

import com.github.javafaker.Faker;
import dao.*;
import modeles.*;

import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

public class Test2 {

    public static void main(final String[] args) throws Exception {
//        createLieu();
//        findAllLieux();
//        findLieu();
//        createUser();
        createVisite();
    }

    private static void createVisite() {
        JpaVisiteDao jpaVisiteDao = new JpaVisiteDao();
        JpaUserDao jpaUserDao = new JpaUserDao();
        JpaCacheDao jpaCacheDao = new JpaCacheDao();
        JpaTypeDao jpaTypeDao = new JpaTypeDao();
        JpaLieuDao jpaLieuDao = new JpaLieuDao();
        try {
            jpaVisiteDao.openSession();
            jpaUserDao.openSession();
            jpaCacheDao.openSession();
            jpaTypeDao.openSession();
            jpaLieuDao.openSession();

            Faker faker = new Faker(new Locale("fr"));

            User user = new User(UUID.randomUUID().toString(), faker.gameOfThrones().dragon(), faker.backToTheFuture().quote());
            if(jpaUserDao.create(user)) {
                System.out.println("User created");
            }

            Type type = new Type(UUID.randomUUID().toString(), faker.witcher().school());
            if(jpaTypeDao.create(type)) {
                System.out.println("Type created");
            }

            Lieu lieu = new Lieu(UUID.randomUUID().toString(), faker.witcher().school());
            if(jpaLieuDao.create(lieu)) {
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
            if(jpaCacheDao.create(cache)) {
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
            if(jpaVisiteDao.create(visite)) {
                System.out.println("visite created");
            }
        }
        finally {
            jpaVisiteDao.closeSession();
        }
    }

    private static void findLieu() {
        JpaLieuDao jpaLieuDao = new JpaLieuDao();
        try {
            jpaLieuDao.openSession();
            Lieu lieu = jpaLieuDao.find("5d6fff34-e79e-4d36-a8d5-aaaa2dd1901f");
            System.out.println(lieu);
        } catch(Exception e) {
            System.out.println(e.toString());
        } finally {
            jpaLieuDao.closeSession();
        }
    }

    private static void createUser() {
        JpaUserDao jpaUserDao = new JpaUserDao();
        try {
            jpaUserDao.openSession();
            Faker faker = new Faker(new Locale("fr"));
            User user = new User(UUID.randomUUID().toString(), faker.gameOfThrones().dragon(), faker.backToTheFuture().quote());
            if(jpaUserDao.create(user)) {
                System.out.println("User created");
            }
        }
        finally {
            jpaUserDao.closeSession();
        }
    }

    private static void findAllLieux() {
        JpaLieuDao jpaLieuDao = new JpaLieuDao();
        try {
            jpaLieuDao.openSession();
            Collection<Lieu> allLieux = jpaLieuDao.findAll();
            for (Lieu lieu : allLieux) {
                System.out.println(lieu);
            }
        } catch(Exception e) {
            System.out.println(e.toString());
        } finally {
            jpaLieuDao.closeSession();
        }
    }

    private static void createLieu() {
        JpaLieuDao jpaLieuDao = new JpaLieuDao();
        try {
            jpaLieuDao.openSession();
            Faker faker = new Faker(new Locale("fr"));
            Lieu lieu = new Lieu(UUID.randomUUID().toString(), faker.gameOfThrones().city());
            if(jpaLieuDao.create(lieu)) {
                System.out.println("Lieu created");
            }
        }
        finally {
            jpaLieuDao.closeSession();
        }


    }
}
