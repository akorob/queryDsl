package hello;

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

public class HelloCat {
  public static void main(String[] args) {

    System.out.println("Maaau!");


    EntityManagerFactory emf = Persistence.createEntityManagerFactory("unit");
    EntityManager entityManager = emf.createEntityManager();
    DbHelper.createTestDb(entityManager);

    likeExample(entityManager);
    nestedObjFieldLikeExample(entityManager);


    entityManager.close();
    emf.close();
  }

  private static void likeExample (EntityManager em){
    System.out.println("############################## Cat names like: %ge%");
    JPAQuery queryFactory = new JPAQuery(em);
    QCat cat = QCat.cat;
    List<String> catNames = queryFactory.from(cat)
            .where(cat.name.like("%ge%"))
            .groupBy(cat.name)
            .list(cat.name);
    catNames.forEach(System.out::println);
  }

  private static void nestedObjFieldLikeExample (EntityManager em){
    System.out.println("############################## Owner names like: %ill%");
    JPAQuery queryFactory = new JPAQuery(em);
    QCat cat = QCat.cat;
    QOwner owner = QOwner.owner;
    //QCat cat = new QCat("myCat");
    //QOwner owner = new QOwner("myOwner");
    List<Cat> catNames = queryFactory.from(cat)
            .leftJoin(cat.owners, owner)
            .on(owner.name.eq("Bill"))
            .list(cat);
    catNames.forEach(System.out::println);
  }

  //http://sqlfiddle.com/#!9/b5f881d/1/0
}