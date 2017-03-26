package hello;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Cat.class)
public abstract class Cat_ {

	public static volatile SingularAttribute<Cat, String> name;
	public static volatile ListAttribute<Cat, Owner> owners;
	public static volatile SingularAttribute<Cat, Integer> id;

}

