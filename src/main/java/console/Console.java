package console;

import java.util.*;

import com.github.javafaker.Faker;
import dao.*;
import modeles.*;


public class Console {

    private Scanner scanner;
    private Faker faker;

    private String choiceEntry;
    private Map<String, Object> daoMap = new HashMap<String, Object>();

    public static void main(String[] args) {
        Console c = new Console();
    }

    public Console() {
        this.scanner = new Scanner(System.in);
        this.faker = new Faker(new Locale("fr"));
        this.run();
    }

    private void homeMenu() {
        System.out.println("1 - Users");
        System.out.println("2 - Caches");
        System.out.println("3 - Visite");
        System.out.println("4 - Type");
        System.out.println("5 - Lieu");
        System.out.println("Taper exit pour sortir");
    }

    private void menuDepth1(String entity) {
        System.out.println("1 - Create " + entity);
        System.out.println("2 - FindAll");
        System.out.println("3 - Find " + entity);
        System.out.println("4 - Update " + entity);
        System.out.println("5 - Delete " + entity);
    }

    private void initDao(String mode) {
        switch (mode.toLowerCase()) {
            case "jpa":
                daoMap.put("user", JpaUserDao.getInstance());
                daoMap.put("cache", JpaCacheDao.getInstance());
                daoMap.put("visite", JpaVisiteDao.getInstance());
                daoMap.put("type", JpaTypeDao.getInstance());
                daoMap.put("lieu", JpaLieuDao.getInstance());
                break;
            case "mongo":
                daoMap.put("user", MongoUserDao.getInstance());
                daoMap.put("cache", MongoCacheDao.getInstance());
                daoMap.put("visite", MongoVisiteDao.getInstance());
                daoMap.put("type", MongoTypeDao.getInstance());
                daoMap.put("lieu", MongoLieuDao.getInstance());
                break;
            default:
                System.out.println("Mauvais type demandé, merci de spécifier JPA ou Mongo");
                System.exit(-2);
        }
    }

    private void run() {
        System.out.println("Mode de stockage de données ? [JPA/Mongo]");
        choiceEntry = this.scanner.next();

        initDao(choiceEntry);

        do {
            this.homeMenu();

            choiceEntry = this.scanner.next();

            switch (choiceEntry)
            {
                case "1":
                    menuUser();
                    break;
                case "2":
                    menuCache();
                    break;
                case "3":
                    menuVisite();
                    break;
                case "4":
                    menuType();
                    break;
                case "5":
                    menuLieu();
                    break;
                case "exit":
                    break;
                default:
                    System.out.println("Choix invalide");
            }
        } while (!choiceEntry.equals("exit"));
    }

    private void menuUser() {
        UserDao dao = (UserDao) daoMap.get("user");
        dao.openSession();

        User user;
        do {
            this.menuDepth1("user");
            System.out.println("Taper return revenir au menu principal");

            choiceEntry = this.scanner.next();

            switch (choiceEntry)
            {
                case "1":
                    user = new User(UUID.randomUUID().toString(), faker.harryPotter().character(), faker.backToTheFuture().quote());
                    boolean isCreated = dao.create(user);
                    if (isCreated) {
                        System.out.println("User créé");
                        System.out.println(user);
                    } else {
                        System.out.println("Une erreur est survenue lors de la création");
                    }
                    break;
                case "2":
                    Collection<User> users = dao.findAll();
                    for (User data : users) {
                        System.out.println(data.toString());
                    }
                    break;
                case "3":
                    System.out.print("Id : ");
                    choiceEntry = this.scanner.next();
                    user = dao.find(choiceEntry);
                    if (null != user) {
                        System.out.println(user.toString());
                    } else {
                        System.out.println("Le user n'existe pas");
                    }
                    break;
                case "4":
                    System.out.print("Id : ");
                    choiceEntry = this.scanner.next();
                    user = dao.find(choiceEntry);
                    if (null != user) {
                        user.setDescription(faker.witcher().quote());
                        boolean isUpdated = dao.update(user);
                        if (isUpdated) {
                            System.out.println(user.toString());
                        } else {
                            System.out.println("Une erreur est survenue lors de la mise à jour");
                        }
                    } else {
                        System.out.println("Le user n'existe pas");
                    }
                    break;
                case "5":
                    System.out.print("Id : ");
                    choiceEntry = this.scanner.next();

                    user = dao.find(choiceEntry);
                    boolean isDeleted;
                    if (null != user) {
                        isDeleted = dao.delete(user);
                    } else {
                        isDeleted = false;
                        System.out.println("Le user n'existe pas");
                    }
                    if (isDeleted) {
                        System.out.println("User supprimé");
                    } else {
                        System.out.println("Une erreur est survenue lors de la suppression");
                    }
                    break;
                case "return":
                    break;
                default:
                    System.out.println("Choix invalide");
            }
        } while(!choiceEntry.equals("return"));
        dao.closeSession();
    }

    private void menuCache() {
        UserDao userDao = (UserDao) daoMap.get("user");
        CacheDao cacheDao = (CacheDao) daoMap.get("cache");
        TypeDao typeDao = (TypeDao) daoMap.get("type");
        LieuDao lieuDao = (LieuDao) daoMap.get("lieu");
        userDao.openSession();
        cacheDao.openSession();
        typeDao.openSession();
        lieuDao.openSession();

        Cache cache;
        Collection<Cache> caches;
        do {
            this.menuDepth1("cache");

            System.out.println("6 - FindByUser");
            System.out.println("7 - FindByType");
            System.out.println("8 - FindByLieu");
            System.out.println("Taper return revenir au menu principal");

            choiceEntry = this.scanner.next();

            switch (choiceEntry)
            {
                case "1":
                    User user = (User) userDao.findAll().toArray()[0];
                    Type type = (Type) typeDao.findAll().toArray()[0];
                    Lieu lieu = (Lieu) lieuDao.findAll().toArray()[0];
                    if (null == user) {
                        System.out.println("Créer un user avant.");
                        return;
                    } else if (null == type) {
                        System.out.println("Créer un type avant.");
                        return;
                    } else if (null == lieu) {
                        System.out.println("Créer un lieu avant.");
                        return;
                    }
                    cache = new Cache(
                            UUID.randomUUID().toString(),
                            faker.address().latitude() + '/' + faker.address().longitude(),
                            "active",
                            "physique",
                            type,
                            lieu,
                            user
                    );
                    boolean isCreated = cacheDao.create(cache);
                    if (isCreated) {
                        System.out.println("Cache créée");
                        System.out.println(cache);
                    } else {
                        System.out.println("Une erreur est survenue lors de la création");
                    }
                    break;
                case "2":
                    caches = cacheDao.findAll();
                    for (Cache data : caches) {
                        System.out.println(data.toString());
                    }
                    break;
                case "3":
                    System.out.print("Id : ");
                    choiceEntry = this.scanner.next();
                    cache = cacheDao.find(choiceEntry);
                    if (null != cache) {
                        System.out.println(cache.toString());
                    } else {
                        System.out.println("La cache n'existe pas");
                    }
                    break;
                case "4":
                    System.out.print("Id : ");
                    choiceEntry = this.scanner.next();
                    cache = cacheDao.find(choiceEntry);
                    if (null != cache) {
                        cache.setEtat("suspendue");
                        boolean isUpdated = cacheDao.update(cache);
                        if (isUpdated) {
                            System.out.println(cache.toString());
                        } else {
                            System.out.println("Une erreur est survenue lors de la mise à jour");
                        }
                    } else {
                        System.out.println("La cache n'existe pas");
                    }
                    break;
                case "5":
                    System.out.print("Id : ");
                    choiceEntry = this.scanner.next();

                    cache = cacheDao.find(choiceEntry);
                    boolean isDeleted;
                    if (null != cache) {
                        isDeleted = cacheDao.delete(cache);
                    } else {
                        isDeleted = false;
                        System.out.println("La cache n'existe pas");
                    }
                    if (isDeleted) {
                        System.out.println("Cache supprimée");
                    } else {
                        System.out.println("Une erreur est survenue lors de la suppression");
                    }
                    break;
                case "6":
                    System.out.print("Id user : ");
                    choiceEntry = this.scanner.next();

                    User userSearch = userDao.find(choiceEntry);
                    if (null == userSearch) {
                        System.out.println("Le user n'existe pas.");
                        break;
                    }
                    caches = cacheDao.getCachesByUser(userSearch);
                    for (Cache data : caches) {
                        System.out.println(data.toString());
                    }
                    break;
                case "7":
                    System.out.print("Id type : ");
                    choiceEntry = this.scanner.next();

                    Type typeSearch = typeDao.find(choiceEntry);
                    if (null == typeSearch) {
                        System.out.println("Le type n'existe pas.");
                        break;
                    }
                    caches = cacheDao.getCachesByType(typeSearch);
                    for (Cache data : caches) {
                        System.out.println(data.toString());
                    }
                    break;
                case "8":
                    System.out.print("Id lieu : ");
                    choiceEntry = this.scanner.next();

                    Lieu lieuSearch = lieuDao.find(choiceEntry);
                    if (null == lieuSearch) {
                        System.out.println("Le lieu n'existe pas.");
                        break;
                    }
                    caches = cacheDao.getCachesByLieu(lieuSearch);
                    for (Cache data : caches) {
                        System.out.println(data.toString());
                    }
                    break;
                case "return":
                    break;
                default:
                    System.out.println("Choix invalide");
            }
        } while(!choiceEntry.equals("return"));
        userDao.closeSession();
        typeDao.closeSession();
        cacheDao.closeSession();
        lieuDao.closeSession();
    }

    private void menuVisite() {
        VisiteDao visiteDao = (VisiteDao) daoMap.get("visite");
        UserDao userDao = (UserDao) daoMap.get("user");
        CacheDao cacheDao = (CacheDao) daoMap.get("cache");
        visiteDao.openSession();
        userDao.openSession();
        cacheDao.openSession();

        Visite visite;
        Collection<Visite> visites;
        do {
            this.menuDepth1("visite");

            System.out.println("6 - FindByCache");
            System.out.println("7 - FindByUser");
            System.out.println("Taper return revenir au menu principal");

            choiceEntry = this.scanner.next();

            switch (choiceEntry)
            {
                case "1":
                    User user = (User) userDao.findAll().toArray()[0];
                    Cache cache = (Cache) cacheDao.findAll().toArray()[0];
                    if (null == user) {
                        System.out.println("Créer un user avant.");
                        return;
                    } else if (null == cache) {
                        System.out.println("Créer une cache avant.");
                        return;
                    }
                    visite = new Visite(
                            UUID.randomUUID().toString(),
                            "",
                            faker.date().birthday(),
                            faker.chuckNorris().fact(),
                            true,
                            cache,
                            user
                    );
                    boolean isCreated = visiteDao.create(visite);
                    if (isCreated) {
                        System.out.println("Visite créée");
                        System.out.println(visite);
                    } else {
                        System.out.println("Une erreur est survenue lors de la création");
                    }
                    break;
                case "2":
                    visites = visiteDao.findAll();
                    for (Visite data : visites) {
                        System.out.println(data.toString());
                    }
                    break;
                case "3":
                    System.out.print("Id : ");
                    choiceEntry = this.scanner.next();
                    visite = visiteDao.find(choiceEntry);
                    if (null != visite) {
                        System.out.println(visite.toString());
                    } else {
                        System.out.println("La visite n'existe pas");
                    }
                    break;
                case "4":
                    System.out.print("Id : ");
                    choiceEntry = this.scanner.next();
                    visite = visiteDao.find(choiceEntry);
                    if (null != visite) {
                        visite.setCommentaire("suspendue");
                        boolean isUpdated = visiteDao.update(visite);
                        if (isUpdated) {
                            System.out.println(visite.toString());
                        } else {
                            System.out.println("Une erreur est survenue lors de la mise à jour");
                        }
                    } else {
                        System.out.println("La visite n'existe pas");
                    }
                    break;
                case "5":
                    System.out.print("Id : ");
                    choiceEntry = this.scanner.next();

                    visite = visiteDao.find(choiceEntry);
                    boolean isDeleted;
                    if (null != visite) {
                        isDeleted = visiteDao.delete(visite);
                    } else {
                        isDeleted = false;
                        System.out.println("La visite n'existe pas");
                    }
                    if (isDeleted) {
                        System.out.println("Visite supprimée");
                    } else {
                        System.out.println("Une erreur est survenue lors de la suppression");
                    }
                    break;
                case "6":
                    System.out.print("Id cache : ");
                    choiceEntry = this.scanner.next();

                    Cache cacheSearch = cacheDao.find(choiceEntry);
                    if (null == cacheSearch) {
                        System.out.println("La cache n'existe pas.");
                        break;
                    }
                    visites = visiteDao.getVisitesByCache(cacheSearch);
                    for (Visite data : visites) {
                        System.out.println(data.toString());
                    }
                    break;
                case "7":
                    System.out.print("Id user : ");
                    choiceEntry = this.scanner.next();

                    User userSearch = userDao.find(choiceEntry);
                    if (null == userSearch) {
                        System.out.println("Le user n'existe pas.");
                        break;
                    }
                    visites = visiteDao.getVisitesByUser(userSearch);
                    for (Visite data : visites) {
                        System.out.println(data.toString());
                    }
                    break;
                case "return":
                    break;
                default:
                    System.out.println("Choix invalide");
            }
        } while(!choiceEntry.equals("return"));
        userDao.closeSession();
        visiteDao.closeSession();
        cacheDao.closeSession();
    }

    private void menuLieu() {
        LieuDao dao = (LieuDao) daoMap.get("lieu");
        dao.openSession();

        Lieu lieu;
        do {
            this.menuDepth1("lieu");
            System.out.println("Taper return revenir au menu principal");

            choiceEntry = this.scanner.next();

            switch (choiceEntry)
            {
                case "1":
                    lieu = new Lieu(UUID.randomUUID().toString(), faker.country().capital());
                    boolean isCreated = dao.create(lieu);
                    if (isCreated) {
                        System.out.println("Lieu créé");
                        System.out.println(lieu);
                    } else {
                        System.out.println("Une erreur est survenue lors de la création");
                    }
                    break;
                case "2":
                    Collection<Lieu> lieux = dao.findAll();
                    for (Lieu data : lieux) {
                        System.out.println(data.toString());
                    }
                    break;
                case "3":
                    System.out.print("Id : ");
                    choiceEntry = this.scanner.next();
                    lieu = dao.find(choiceEntry);
                    if (null != lieu) {
                        System.out.println(lieu.toString());
                    } else {
                        System.out.println("Le lieu n'existe pas");
                    }
                    break;
                case "4":
                    System.out.print("Id : ");
                    choiceEntry = this.scanner.next();
                    lieu = dao.find(choiceEntry);
                    if (null != lieu) {
                        lieu.setGeoData(faker.address().city());
                        boolean isUpdated = dao.update(lieu);
                        if (isUpdated) {
                            System.out.println(lieu.toString());
                        } else {
                            System.out.println("Une erreur est survenue lors de la mise à jour");
                        }
                    } else {
                        System.out.println("Le lieu n'existe pas");
                    }
                    break;
                case "5":
                    System.out.print("Id : ");
                    choiceEntry = this.scanner.next();

                    lieu = dao.find(choiceEntry);
                    boolean isDeleted;
                    if (null != lieu) {
                        isDeleted = dao.delete(lieu);
                    } else {
                        isDeleted = false;
                        System.out.println("Le lieu n'existe pas");
                    }
                    if (isDeleted) {
                        System.out.println("Lieu supprimé");
                    } else {
                        System.out.println("Une erreur est survenue lors de la suppression");
                    }
                    break;
                case "return":
                    break;
                default:
                    System.out.println("Choix invalide");
            }
        } while(!choiceEntry.equals("return"));
        dao.closeSession();
    }

    private void menuType() {
        TypeDao dao = (TypeDao) daoMap.get("type");
        dao.openSession();

        Type type;
        do {
            this.menuDepth1("type");
            System.out.println("Taper return revenir au menu principal");

            choiceEntry = this.scanner.next();

            switch (choiceEntry)
            {
                case "1":
                    type = new Type(UUID.randomUUID().toString(), "traditionnelle");
                    boolean isCreated = dao.create(type);
                    if (isCreated) {
                        System.out.println("Type créé");
                        System.out.println(type);
                    } else {
                        System.out.println("Une erreur est survenue lors de la création");
                    }
                    break;
                case "2":
                    Collection<Type> types = dao.findAll();
                    for (Type data : types) {
                        System.out.println(data.toString());
                    }
                    break;
                case "3":
                    System.out.print("Id : ");
                    choiceEntry = this.scanner.next();
                    type = dao.find(choiceEntry);
                    if (null != type) {
                        System.out.println(type.toString());
                    } else {
                        System.out.println("Le type n'existe pas");
                    }
                    break;
                case "4":
                    System.out.print("Id : ");
                    choiceEntry = this.scanner.next();
                    type = dao.find(choiceEntry);
                    if (null != type) {
                        type.setType(faker.food().fruit());
                        boolean isUpdated = dao.update(type);
                        if (isUpdated) {
                            System.out.println(type.toString());
                        } else {
                            System.out.println("Une erreur est survenue lors de la mise à jour");
                        }
                    } else {
                        System.out.println("Le type n'existe pas");
                    }
                    break;
                case "5":
                    System.out.print("Id : ");
                    choiceEntry = this.scanner.next();

                    type = dao.find(choiceEntry);
                    boolean isDeleted;
                    if (null != type) {
                        isDeleted = dao.delete(type);
                    } else {
                        isDeleted = false;
                        System.out.println("Le type n'existe pas");
                    }
                    if (isDeleted) {
                        System.out.println("Type supprimé");
                    } else {
                        System.out.println("Une erreur est survenue lors de la suppression");
                    }
                    break;
                case "return":
                    break;
                default:
                    System.out.println("Choix invalide");
            }
        } while(!choiceEntry.equals("return"));
        dao.closeSession();
    }
}
