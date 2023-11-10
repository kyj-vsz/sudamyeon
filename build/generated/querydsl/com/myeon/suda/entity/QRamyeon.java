package com.myeon.suda.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QRamyeon is a Querydsl query type for Ramyeon
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QRamyeon extends EntityPathBase<Ramyeon> {

    private static final long serialVersionUID = -1213106678L;

    public static final QRamyeon ramyeon = new QRamyeon("ramyeon");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final StringPath birth = createString("birth");

    public final StringPath brand = createString("brand");

    public final NumberPath<Long> calorie = createNumber("calorie", Long.class);

    public final NumberPath<Long> carbohydrate = createNumber("carbohydrate", Long.class);

    public final StringPath category = createString("category");

    public final NumberPath<Long> cholesterol = createNumber("cholesterol", Long.class);

    public final StringPath content = createString("content");

    public final StringPath cooking = createString("cooking");

    public final StringPath cooktime = createString("cooktime");

    public final StringPath etc = createString("etc");

    public final StringPath expiry = createString("expiry");

    public final NumberPath<Long> fat = createNumber("fat", Long.class);

    public final StringPath gname = createString("gname");

    public final NumberPath<Long> mno = createNumber("mno", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modDate = _super.modDate;

    public final NumberPath<Long> noodleSize = createNumber("noodleSize", Long.class);

    public final StringPath price = createString("price");

    public final NumberPath<Long> protein = createNumber("protein", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> regDate = _super.regDate;

    public final NumberPath<Long> saturatedFat = createNumber("saturatedFat", Long.class);

    public final NumberPath<Long> sodum = createNumber("sodum", Long.class);

    public final NumberPath<Long> soupnum = createNumber("soupnum", Long.class);

    public final NumberPath<Long> spicy = createNumber("spicy", Long.class);

    public final NumberPath<Long> sugar = createNumber("sugar", Long.class);

    public final NumberPath<Long> transFat = createNumber("transFat", Long.class);

    public final NumberPath<Long> weight = createNumber("weight", Long.class);

    public QRamyeon(String variable) {
        super(Ramyeon.class, forVariable(variable));
    }

    public QRamyeon(Path<? extends Ramyeon> path) {
        super(path.getType(), path.getMetadata());
    }

    public QRamyeon(PathMetadata metadata) {
        super(Ramyeon.class, metadata);
    }

}

