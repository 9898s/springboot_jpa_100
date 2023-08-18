drop table if exists notice;

create table notice
(
    id       bigint auto_increment primary key,
    title    varchar(255),
    contents varchar(255),

    hits     integer,
    likes    integer,

    reg_date timestamp,
    update_date timestamp
);