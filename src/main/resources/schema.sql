drop table if exists user;
drop table if exists notice;

create table user
(
    id          bigint auto_increment primary key,
    email       varchar(255),
    user_name   varchar(255),
    password    varchar(255),
    phone       varchar(255),
    reg_date    timestamp,
    update_date timestamp,
    status      integer,
    lock_yn     boolean
);

create table notice
(
    id           bigint auto_increment primary key,
    title        varchar(255),
    contents     varchar(255),

    hits         integer,
    likes        integer,

    reg_date     timestamp,
    update_date  timestamp,
    deleted_date timestamp,
    deleted      boolean,

    user_id      bigint,
    constraint FK_NOTICE_USER_ID foreign key (user_id) references user (id)
);

create table notice_like
(
    id        bigint auto_increment primary key,
    notice_id bigint,
    user_id   bigint not null,
    constraint FK_NOTICE_LIKE_NOTICE_ID foreign key (notice_id) references notice (id),
    constraint FK_NOTICE_LIKE_USER_ID foreign key (user_id) references user (id)
);

create table user_login_history
(
    id         bigint auto_increment primary key,
    user_id    bigint,
    email      varchar(255),
    user_name  varchar(255),
    login_date timestamp,
    ip_addr    varchar(255)
)