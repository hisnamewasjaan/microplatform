create table ads
(
    id               varchar(255) not null,
    ad_status        int4,
    expires          timestamp,
    amount           numeric(19, 2),
    currency         varchar(255),
    seller_id        varchar(255),
    version          int8,
    item_for_sale_id varchar(255) not null,
    primary key (id)
)
;
create table item_for_sale
(
    id          varchar(255) not null,
    description varchar(255),
    name        varchar(255),
    version     int8,
    primary key (id)
)
;
create table item_for_sale_images
(
    item_for_sale_id varchar(255) not null,
    data             bytea
)
;
alter table if exists ads
    add constraint FKsqn4ipm3k88dbpbop9axjv87l foreign key (item_for_sale_id) references item_for_sale
;
alter table if exists item_for_sale_images
    add constraint FKk6dwuacyyuio3b3ypgd2scwcq foreign key (item_for_sale_id) references item_for_sale
;
