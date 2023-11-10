package com.myeon.suda.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QFreeBoardReply is a Querydsl query type for FreeBoardReply
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QFreeBoardReply extends EntityPathBase<FreeBoardReply> {

    private static final long serialVersionUID = 988858639L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QFreeBoardReply freeBoardReply = new QFreeBoardReply("freeBoardReply");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final QFreeBoard board;

    public final StringPath freeBoardReplyContent = createString("freeBoardReplyContent");

    public final NumberPath<Long> freeBoardRno = createNumber("freeBoardRno", Long.class);

    public final QMember member;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modDate = _super.modDate;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> regDate = _super.regDate;

    public QFreeBoardReply(String variable) {
        this(FreeBoardReply.class, forVariable(variable), INITS);
    }

    public QFreeBoardReply(Path<? extends FreeBoardReply> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QFreeBoardReply(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QFreeBoardReply(PathMetadata metadata, PathInits inits) {
        this(FreeBoardReply.class, metadata, inits);
    }

    public QFreeBoardReply(Class<? extends FreeBoardReply> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.board = inits.isInitialized("board") ? new QFreeBoard(forProperty("board"), inits.get("board")) : null;
        this.member = inits.isInitialized("member") ? new QMember(forProperty("member")) : null;
    }

}

