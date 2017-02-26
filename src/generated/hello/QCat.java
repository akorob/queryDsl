package hello;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QCat is a Querydsl query type for Cat
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QCat extends EntityPathBase<Cat> {

    private static final long serialVersionUID = 1195528346L;

    public static final QCat cat = new QCat("cat");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath name = createString("name");

    public QCat(String variable) {
        super(Cat.class, forVariable(variable));
    }

    public QCat(Path<? extends Cat> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCat(PathMetadata<?> metadata) {
        super(Cat.class, metadata);
    }

}

