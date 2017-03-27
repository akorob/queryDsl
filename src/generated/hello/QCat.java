package hello;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QCat is a Querydsl query type for Cat
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QCat extends EntityPathBase<Cat> {

    private static final long serialVersionUID = 1195528346L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCat cat = new QCat("cat");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath name = createString("name");

    public final QOwner owner;

    public QCat(String variable) {
        this(Cat.class, forVariable(variable), INITS);
    }

    public QCat(Path<? extends Cat> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QCat(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QCat(PathMetadata<?> metadata, PathInits inits) {
        this(Cat.class, metadata, inits);
    }

    public QCat(Class<? extends Cat> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.owner = inits.isInitialized("owner") ? new QOwner(forProperty("owner")) : null;
    }

}

