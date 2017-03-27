package hello;

import com.mysema.query.jpa.impl.JPAQuery;
import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

import javax.persistence.*;
import javax.persistence.criteria.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HelloCat {
  public static void main(String[] args) {

    System.out.println("Maaau!");
    //http://sqlfiddle.com/#!9/8d1a92/7/0

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
    System.out.println("############################## Owner names equals(QueryDsl): Bill");

    JPAQuery queryFactory = new JPAQuery(em);
    QCat cat = QCat.cat;
    QOwner owner = QOwner.owner;
    List<Cat> catNames = queryFactory.from(cat)
            .leftJoin(cat.owner, owner)
            .where(owner.name.eq("Bill"))
            .list(cat);
    catNames.forEach(System.out::println);

    System.out.println("############################## Owner names equals(CriteriaQuery): Bill");
    CriteriaBuilder cb = em.getCriteriaBuilder();
    CriteriaQuery<Cat> criteriaQuery = cb.createQuery(Cat.class);
    Root<Cat> catQ = criteriaQuery.from(Cat.class);
    Join<Cat, Owner> ownerQ = catQ.join(Cat_.owner);
    criteriaQuery.where(cb.equal(ownerQ.get(Owner_.name), "Bill"));
    Query query = em.createQuery(criteriaQuery);
    List<Cat> catNamesQ = query.getResultList();
    catNamesQ.forEach(System.out::println);
  }


}