insert into user (email, password, phone, reg_date, update_date, user_name) values ('test1@test.com', '1111', '010-1111-1111', now(), null, '홍길동');
insert into user (email, password, phone, reg_date, update_date, user_name) values ('test2@test.com', '2222', '010-2222-2222', now(), null, '손흥민');
insert into user (email, password, phone, reg_date, update_date, user_name) values ('test3@test.com', '3333', '010-3333-3333', now(), null, '봉준호');
insert into user (email, password, phone, reg_date, update_date, user_name) values ('test4@test.com', '4444', '010-4444-4444', now(), null, '박재범');

insert into notice (contents, hits, likes, reg_date, title, deleted, user_id) values ('내용', 0, 0, '2023-08-18 01:01:01.000000', '제목1', 0, 1);
insert into notice (contents, hits, likes, reg_date, title, deleted, user_id) values ('내용', 0, 0, '2023-08-18 01:01:01.000000', '제목2', 0, 1);
insert into notice (contents, hits, likes, reg_date, title, deleted, user_id) values ('내용', 0, 0, '2023-08-18 01:01:01.000000', '제목3', 0, 1);