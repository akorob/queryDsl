package hello;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Owner.class)
public abstract class Owner_ {

	public static volatile ListAttribute<Owner, Cat> cats;
	public static volatile SingularAttribute<Owner, String> name;
	public static volatile SingularAttribute<Owner, Integer> id;

}

