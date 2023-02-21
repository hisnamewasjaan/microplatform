create table IF NOT EXISTS ITEM_FOR_SALE
(
    ID          CHARACTER VARYING(255) not null
        primary key,
    DESCRIPTION CHARACTER VARYING(255),
    NAME        CHARACTER VARYING(255),
    VERSION     BIGINT
);

create table IF NOT EXISTS ADS
(
    ID               CHARACTER VARYING(255) not null
        primary key,
    AD_STATUS        INTEGER,
    EXPIRES          TIMESTAMP,
    AMOUNT           NUMERIC(19, 2),
    CURRENCY         CHARACTER VARYING(255),
    SELLER_ID        CHARACTER VARYING(255),
    VERSION          BIGINT,
    ITEM_FOR_SALE_ID CHARACTER VARYING(255) not null,
    constraint FKSQN4IPM3K88DBPBOP9AXJV87L
        foreign key (ITEM_FOR_SALE_ID) references ITEM_FOR_SALE
);

create table IF NOT EXISTS ITEM_FOR_SALE_IMAGES
(
    ITEM_FOR_SALE_ID CHARACTER VARYING(255) not null,
    DATA             bytea                  not null,
    constraint FKK6DWUACYYUIO3B3YPGD2SCWCQ
        foreign key (ITEM_FOR_SALE_ID) references ITEM_FOR_SALE
);


create table ads (
    id varchar(255) not null,
    ad_status int4,
    expires timestamp,
    amount numeric(19, 2),
    currency varchar(255),
    seller_id varchar(255),
    version int8,
    item_for_sale_id varchar(255) not null,
    primary key (id)
);

create table item_for_sale (
    id varchar(255) not null,
    description varchar(255),
    name varchar(255),
    version int8,
    primary key (id)
);

create table item_for_sale_images (
    item_for_sale_id varchar(255) not null,
    data bytea
);

alter table if exists ads
    add constraint FKsqn4ipm3k88dbpbop9axjv87l
        foreign key (item_for_sale_id)
            references item_for_sale
;

alter table if exists item_for_sale_images
    add constraint FKk6dwuacyyuio3b3ypgd2scwcq
        foreign key (item_for_sale_id)
            references item_for_sale
;
