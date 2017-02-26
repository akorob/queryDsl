package hello;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

/**
 * Created by User on 26.02.2017.
 */
public class DbHelper {
    public static void createTestDb(EntityManager entityManager){
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        entityManager.persist(new Cat("Kitty", new Owner("John")));
        entityManager.persist(new Cat("Tiger", new Owner("Ron")));
        entityManager.persist(new Cat("Tiger", new Owner("Bill")));
        entityManager.persist(new Cat("Angel", new Owner("Monica")));
        entityManager.persist(new Cat("Loki", new Owner("Martin")));
        entityManager.persist(new Cat("Loki", null));

        entityManager.flush();
        transaction.commit();
    }
}
