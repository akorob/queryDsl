package hello;

import org.joda.time.LocalTime;

import com.mysema.query.jpa.impl.JPAQuery;
import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static hello.QCat.cat;

public class HelloCat {
  public static void main(String[] args) {
    //test gradle dependency on Joda time
    LocalTime currentTime = new LocalTime();
    System.out.println("The current local time is: " + currentTime);


    Greeter greeter = new Greeter();
    System.out.println(greeter.sayMau());


    EntityManagerFactory emf = Persistence.createEntityManagerFactory("unit");

    EntityManager entityManager = emf.createEntityManager();

    EntityTransaction transaction = entityManager.getTransaction();
    transaction.begin();
    entityManager.persist(new Cat("Smith"));
    entityManager.persist(new Cat("Gates"));
    entityManager.persist(new Cat("Orlov"));
    entityManager.persist(new Cat("Smirnov"));
    entityManager.persist(new Cat("Orlov"));

    entityManager.flush();
    transaction.commit();

    JPAQuery query = new JPAQuery(entityManager);

    List<String> uniqueUserNames = query.from(cat)
            .where(cat.name.like("%ov"))
            .groupBy(cat.name)
            .list(cat.name);

    System.out.println("Unique names:");
    for (String uniqueUserName : uniqueUserNames) {
      System.out.println(uniqueUserName);
    }

    entityManager.close();
    emf.close();



  }
}