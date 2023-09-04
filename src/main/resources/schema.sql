drop table if exists user;
drop table if exists notice;

create table user
(
    id                 bigint auto_increment primary key,
    email              varchar(255),
    user_name          varchar(255),
    password           varchar(255),
    phone              varchar(255),
    reg_date           timestamp,
    update_date        timestamp,
    status             integer,
    lock_yn            boolean,
    password_reset_yn  boolean,
    password_reset_key varchar(255)
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
);

create table board_type
(
    id          bigint auto_increment primary key,
    board_name  varchar(255),
    reg_date    timestamp,
    update_date timestamp,

    using_yn    boolean
);

create table board
(
    id                 bigint auto_increment primary key,
    title              varchar(255),
    contents           clob,
    reg_date           timestamp,
    board_type_id      bigint,
    user_id            bigint,
    top_yn             boolean,
    publish_start_date date,
    publish_end_date   date,

    replyContents      clob,

    constraint FK_BOARD_BOARD_TYPE_ID foreign key (board_type_id) references board_type (id),
    constraint FK_BOARD_USER_ID foreign key (user_id) references user (id)
);

create table board_hits
(
    id       bigint primary key auto_increment,
    board_id bigint,
    user_id  bigint,
    reg_date timestamp,

    constraint FK_BOARD_HITS_BOARD_ID foreign key (board_id) references board (id),
    constraint FK_BOARD_HITS_USER_ID foreign key (user_id) references user (id)
);

create table board_like
(
    id       bigint primary key auto_increment,
    board_id bigint,
    user_id  bigint,
    reg_date timestamp,

    constraint FK_BOARD_LIKE_BOARD_ID foreign key (board_id) references board (id),
    constraint FK_BOARD_LIKE_USER_ID foreign key (user_id) references user (id)
);

create table board_bad_report
(
    id             bigint primary key auto_increment,
    user_id        bigint,
    user_name      varchar(255),
    user_email     varchar(255),
    board_id       bigint,
    board_user_id  bigint,
    board_title    varchar(255),
    board_contents varchar(255),
    board_reg_date timestamp,
    comments       varchar(255),
    reg_date       timestamp
);

create table board_scrap
(
    id             bigint auto_increment primary key,
    user_id        bigint,
    board_id       bigint,
    board_type_id  bigint,
    board_user_id  bigint,
    board_title    varchar(255),
    board_contents varchar(255),
    board_reg_date timestamp,
    reg_date       timestamp,
    constraint FK_BOARD_SCRAP_USER_ID foreign key (user_id) references user (id)
);

create table board_bookmark
(
    id            bigint auto_increment primary key,
    user_id       bigint,
    board_id      bigint,
    board_type_id bigint,
    board_title   varchar(255),
    board_url     varchar(255),
    reg_date      timestamp,
    constraint FK_BOARD_BOOKMARK_USER_ID foreign key (user_id) references user (id)
);

create table user_interest
(
    id               bigint auto_increment primary key,
    user_id          bigint,
    interest_user_id bigint,
    reg_date         timestamp,

    constraint FK_USER_INTEREST_USER_ID foreign key (user_id) references user (id),
    constraint FK_USER_INTEREST_INTEREST_USER_ID foreign key (interest_user_id) references user (id)
);

create table board_comment
(
    id       bigint auto_increment primary key,
    user_id  bigint,
    board_id bigint,
    comments varchar(255),
    reg_date timestamp,

    constraint FK_BOARD_COMMENT_USER_ID foreign key (user_id) references user (id),
    constraint FK_BOARD_COMMENT_BOARD_ID foreign key (board_id) references board (id)
);

create table user_point
(
    id              bigint auto_increment primary key,
    user_id         bigint,
    user_point_type varchar(255),
    point           integer,

    constraint FK_USER_POINT_USER_ID foreign key (user_id) references user (id)
);

create table logs
(
    id       bigint auto_increment primary key,
    text     clob,
    reg_date timestamp
);

create table mail_template
(
    id             bigint primary key auto_increment,
    template_id    varchar(255),
    title          varchar(255),
    contents       varchar(255),
    send_email     varchar(255),
    send_user_name varchar(255),
    reg_date       timestamp
);

