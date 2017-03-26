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
        Owner ron = new Owner("Ron");
        Owner bill = new Owner("Bill");
        Owner monica = new Owner("Monica");
        Owner martin = new Owner("Martin");

       /* entityManager.persist(john);
        entityManager.persist(ron);
        entityManager.persist(bill);
        entityManager.persist(monica);
        entityManager.persist(martin);*/


        entityManager.persist(new Cat("Method", Arrays.asList(bill)));
        entityManager.persist(new Cat("Angel", Arrays.asList(monica)));
        entityManager.persist(new Cat("Tiger", Arrays.asList(bill, monica)));

        entityManager.persist(new Cat("Kitty", Arrays.asList(john)));
        entityManager.persist(new Cat("Tiger", Arrays.asList(ron)));
        entityManager.persist(new Cat("Loki", Arrays.asList(martin)));
        entityManager.persist(new Cat("Loki", Arrays.asList(martin)));

        entityManager.flush();
        transaction.commit();
    }
}
