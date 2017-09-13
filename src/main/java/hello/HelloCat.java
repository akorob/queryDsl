package hello;

import com.mysema.query.*;
import com.mysema.query.group.GroupBy;
import com.mysema.query.jpa.impl.JPAQuery;
import com.mysema.query.types.ExpressionUtils;
import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

import javax.persistence.*;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HelloCat {
    public static void main(String[] args) {

        System.out.println("Maaau!");

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("unit");
        EntityManager em = emf.createEntityManager();
        DbHelper.createTestDb(em);

        simpleLike(em);
        dinamicConditions(em);
        nestedObjFieldEquals(em);
        orderByName(em);
        orderCatsByOwnerName(em);
        findMaxCatAge(em);
        findMaxAgeByName(em);
        catCountByOwner(em);

        em.close();
        emf.close();
    }

    private static void simpleLike(EntityManager em) {
        System.out.println("## Cat names like: %ge%");
        JPAQuery queryFactory = new JPAQuery(em);
        QCat cat = QCat.cat;
        List<String> catNames = queryFactory.from(cat)
                .where(cat.name.like("%ge%"))
                .groupBy(cat.name)
                .list(cat.name);
        catNames.forEach(System.out::println);
    }

    private static void dinamicConditions(EntityManager em) {
        System.out.println("## multipleConditions");
        QCat cat = QCat.cat;
        List<com.mysema.query.types.Predicate> predicates = new ArrayList<>();
        if (true) {
            predicates.add(cat.name.like("%ge%"));
        }
        if (true) {
            predicates.add(cat.age.eq(2));
        }
        com.mysema.query.types.Predicate where = ExpressionUtils.allOf(predicates);
        JPAQuery queryFactory = new JPAQuery(em);
        List<String> catNames = queryFactory.from(cat)
                .where(where)
                .groupBy(cat.name)
                .list(cat.name);
        catNames.forEach(System.out::println);
    }

    @SuppressWarnings("JpaQlInspection")
    private static void nestedObjFieldEquals(EntityManager em) {

        System.out.println("## Owner names equals(JPQL): Bill");
        TypedQuery<Cat> queryJpql = em.createQuery(
                "SELECT c FROM Cat c JOIN c.owner o WHERE o.name=:ownerName", Cat.class);
        queryJpql.setParameter("ownerName", "Bill");
        List<Cat> catsJpql = queryJpql.getResultList();
        catsJpql.forEach(System.out::println);


        System.out.println("## Owner names equals(QueryDsl): Bill");
        JPAQuery queryFactory = new JPAQuery(em);
        QCat cat = QCat.cat;
        QOwner owner = QOwner.owner;
        List<Cat> catsQdsl = queryFactory.from(cat)
                .join(cat.owner, owner)
                .where(owner.name.eq("Bill"))
                .list(cat);
        catsQdsl.forEach(System.out::println);

        System.out.println("## Owner names equals(CriteriaQuery): Bill");
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Cat> criteriaQuery = cb.createQuery(Cat.class);
        Root<Cat> catQ = criteriaQuery.from(Cat.class);
        Join<Cat, Owner> ownerQ = catQ.join(Cat_.owner);
        criteriaQuery.where(cb.equal(ownerQ.get(Owner_.name), "Bill"));
        TypedQuery<Cat> query = em.createQuery(criteriaQuery);
        List<Cat> catsCrQ = query.getResultList();
        catsCrQ.forEach(System.out::println);
    }

    private static void orderByName(EntityManager em) {
        System.out.println("## Cats ordered by asc");
        JPAQuery queryFactory = new JPAQuery(em);
        QCat cat = QCat.cat;
        List<Cat> cats = queryFactory.from(cat)
                .orderBy(cat.name.asc())
                .list(cat);
        cats.forEach(System.out::println);
    }


    private static void orderCatsByOwnerName(EntityManager em) {
        System.out.println("## Cats ordered by owner name desc");
        JPAQuery queryFactory = new JPAQuery(em);
        QCat cat = QCat.cat;
        List<Cat> cats = queryFactory.from(cat)
                .orderBy(cat.owner.name.asc())
                .list(cat);
        cats.forEach(System.out::println);
    }

    private static void findMaxCatAge(EntityManager em) {
        JPAQuery queryFactory = new JPAQuery(em);
        QCat cat = QCat.cat;
        Integer maxAge = queryFactory.from(cat)
                .singleResult(cat.age.max());
        System.out.println("## Max cat age = " + maxAge);
    }

    private static void findMaxAgeByName(EntityManager em) {
        System.out.println("## Max cat age by name = ");
        JPAQuery queryFactory = new JPAQuery(em);
        QCat cat = QCat.cat;
        Map<String, Integer> results = queryFactory.from(cat)
                .transform(GroupBy.groupBy(cat.name).as(GroupBy.max(cat.age)));
        results.entrySet().forEach(t -> System.out.println(t.getKey() + " - " + t.getValue()));
    }

    private static void catCountByOwner(EntityManager em) {
        System.out.println("## Cat count by owner");
        JPAQuery queryFactory = new JPAQuery(em);
        QCat cat = QCat.cat;
        QOwner owner = QOwner.owner;
        List<com.mysema.query.Tuple> results = queryFactory.from(cat)
                .join(cat.owner, owner)
                .groupBy(owner)
                .orderBy(owner.name.asc())
                .list(owner.name, cat.count());
        results.forEach(t -> System.out.println(t.toString()));
    }

}