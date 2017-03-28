package hello;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.Arrays;

/**
 * Created by User on 26.02.2017.
 */
public class DbHelper {
    public static void createTestDb(EntityManager entityManager){


        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        Owner john = new Owner("John");
        Cat kitty = new Cat("Kitty", 1);
        kitty.setOwner(john);
        john.getCats().add(kitty);

        Owner ron = new Owner("Ron");
        Cat tigerJun = new Cat("Tiger", 2);
        tigerJun.setOwner(ron);
        ron.getCats().add(tigerJun);

        Owner bill = new Owner("Bill");
        Cat method = new Cat("Method", 3);
        Cat tiger = new Cat("Tiger", 4);
        method.setOwner(bill);
        tiger.setOwner(bill);
        bill.getCats().add(method);
        bill.getCats().add(tiger);

        Owner monica = new Owner("Monica");
        Cat angel = new Cat("Angel", 2);
        angel.setOwner(monica);
        monica.getCats().add(angel);

        Owner martin = new Owner("Martin");
        Cat loki = new Cat("Loki", 1);
        loki.setOwner(martin);
        martin.getCats().add(loki);

        entityManager.persist(john);
        entityManager.persist(ron);
        entityManager.persist(bill);
        entityManager.persist(monica);
        entityManager.persist(martin);

        entityManager.flush();
        transaction.commit();
    }
}
