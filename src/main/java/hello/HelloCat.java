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
    System.out.println("Maaau!");


    EntityManagerFactory emf = Persistence.createEntityManagerFactory("unit");
    EntityManager entityManager = emf.createEntityManager();
    DbHelper.createTestDb(entityManager);

    JPAQuery query = new JPAQuery(entityManager);

    List<String> catNames = query.from(cat)
            .where(cat.name.like("%ge%"))
            .groupBy(cat.name)
            .list(cat.name);

    System.out.println("##############################Unique names:  ##################################");
    for (String catName : catNames) {
      System.out.println(catName);
    }

    entityManager.close();
    emf.close();

  }
}