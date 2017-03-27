package hello;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QOwner is a Querydsl query type for Owner
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QOwner extends EntityPathBase<Owner> {

    private static final long serialVersionUID = -2136759689L;

    public static final QOwner owner = new QOwner("owner");

    public final ListPath<Cat, QCat> cats = this.<Cat, QCat>createList("cats", Cat.class, QCat.class, PathInits.DIRECT2);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath name = createString("name");

    public QOwner(String variable) {
        super(Owner.class, forVariable(variable));
    }

    public QOwner(Path<? extends Owner> path) {
        super(path.getType(), path.getMetadata());
    }

    public QOwner(PathMetadata<?> metadata) {
        super(Owner.class, metadata);
    }

}

