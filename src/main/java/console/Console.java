package console;

import java.util.Collection;
import java.util.Locale;
import java.util.Scanner;
import java.util.UUID;

import com.github.javafaker.Faker;
import dao.*;
import modeles.*;


public class Console {

    private Scanner scanner;
    private Faker faker;

    private String choiceEntry;

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

    private void run() {
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
        } while (!choiceEntry.equals("return"));
    }
    
    private void menuUser() {
        JpaUserDao dao = JpaUserDao.getInstance();
        dao.openSession();
        System.out.println(dao.toString());
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
                    System.out.println(user.toString());
                    break;
                case "4":
                    break;
                case "5":
                    System.out.print("Id : ");
                    choiceEntry = this.scanner.next();
                    user = dao.find(choiceEntry);
                    boolean isDeleted = dao.delete(user);
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
    }
    
    private void menuCache() {
        JpaCacheDao dao = JpaCacheDao.getInstance();
        dao.openSession();

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
                    break;
                case "2":
                    break;
                case "3":
                    break;
                case "4":
                    break;
                case "5":
                    break;
                case "return":
                    break;
                default:
                    System.out.println("Choix invalide");
            }
        } while(!choiceEntry.equals("return"));
    }
    
    private void menuVisite() {
        JpaVisiteDao dao = JpaVisiteDao.getInstance();
        dao.openSession();

        do {
            this.menuDepth1("visite");

            System.out.println("6 - FindByCache");
            System.out.println("7 - FindByUser");
            System.out.println("Taper return revenir au menu principal");

            choiceEntry = this.scanner.next();

            switch (choiceEntry)
            {
                case "1":
                    break;
                case "2":
                    break;
                case "3":
                    break;
                case "4":
                    break;
                case "5":
                    break;
                case "return":
                    break;
                default:
                    System.out.println("Choix invalide");
            }
        } while(!choiceEntry.equals("return"));
    }
    
    private void menuLieu() {
        JpaLieuDao dao = JpaLieuDao.getInstance();
        dao.openSession();

        do {
            this.menuDepth1("lieu");
            System.out.println("Taper return revenir au menu principal");

            choiceEntry = this.scanner.next();

            switch (choiceEntry)
            {
                case "1":
                    break;
                case "2":
                    break;
                case "3":
                    break;
                case "4":
                    break;
                case "5":
                    break;
                case "return":
                    break;
                default:
                    System.out.println("Choix invalide");
            }
        } while(!choiceEntry.equals("return"));
    }
    
    private void menuType() {
        JpaTypeDao dao = JpaTypeDao.getInstance();
        dao.openSession();

        do {
            this.menuDepth1("type");
            System.out.println("Taper return revenir au menu principal");

            choiceEntry = this.scanner.next();

            switch (choiceEntry)
            {
                case "1":
                    break;
                case "2":
                    break;
                case "3":
                    break;
                case "4":
                    break;
                case "5":
                    break;
                case "return":
                    break;
                default:
                    System.out.println("Choix invalide");
            }
        } while(!choiceEntry.equals("return"));
    }
}
