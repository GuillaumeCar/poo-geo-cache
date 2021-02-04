package Test;

import Utils.PersistUtils;
import com.github.javafaker.Faker;
import modeles.Cache;
import modeles.Lieu;
import modeles.Type;
import modeles.User;
import net.bytebuddy.asm.Advice;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import java.util.Locale;
import java.util.UUID;

public class Test1 {
    private static final SessionFactory ourSessionFactory;

    static {
        try {
            Configuration configuration = new Configuration();
            configuration.configure();

            ourSessionFactory = configuration.buildSessionFactory();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static Session getSession() throws HibernateException {
        return ourSessionFactory.openSession();
    }

    public static void main(final String[] args) throws Exception {
        final Session session = getSession();
        PersistUtils.setEntityManager(session.getEntityManagerFactory().createEntityManager());

        try {
            User user = createUser();
            createCache(user);
        } finally {
            session.close();
        }
    }

    private static User createUser() {
        Faker faker = new Faker(new Locale("fr"));
        User user = new User(UUID.randomUUID().toString(), faker.gameOfThrones().dragon(), faker.chuckNorris().fact());
        PersistUtils.persistEntity(user);
        return user;
    }

    private static void createCache(User user) {
        Faker faker = new Faker(new Locale("fr"));
        Lieu lieu = new Lieu(UUID.randomUUID().toString(), faker.gameOfThrones().city());
        Type type = new Type(UUID.randomUUID().toString(), faker.beer().name());
        Cache cache = new Cache(
                UUID.randomUUID().toString(),
                faker.address().latitude() + "/" +faker.address().longitude(),
                "etat1",
                faker.beer().name(),
                type.getId(),
                lieu.getId(),
                user);

        PersistUtils.persistEntity(lieu);
        PersistUtils.persistEntity(type);
        PersistUtils.persistEntity(cache);

    }
}
