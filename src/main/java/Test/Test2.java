package Test;

import com.github.javafaker.Faker;
import dao.JpaLieuDao;
import modeles.Lieu;

import java.util.Locale;
import java.util.UUID;

public class Test2 {

    public static void main(final String[] args) throws Exception {
        createLieu();
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
