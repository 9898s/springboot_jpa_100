insert into user (email, password, phone, reg_date, update_date, user_name, status, lock_yn)
values ('test1@test.com', '1111', '010-1111-1111', now(), null, '홍길동', 1, 0)
     , ('test2@test.com', '2222', '010-2222-2222', now(), null, '손흥민', 1, 0)
     , ('test3@test.com', '3333', '010-3333-3333', now(), null, '봉준호', 1, 0)
     , ('test4@test.com', '4444', '010-4444-4444', now(), null, '박재범', 2, 0);

insert into notice (contents, hits, likes, reg_date, title, deleted, user_id)
values ('내용', 0, 0, '2023-08-18 01:01:01.000000', '제목1', 0, 1);
insert into notice (contents, hits, likes, reg_date, title, deleted, user_id)
values ('내용', 0, 0, '2023-08-18 01:01:01.000000', '제목2', 0, 1);
insert into notice (contents, hits, likes, reg_date, title, deleted, user_id)
values ('내용', 0, 0, '2023-08-18 01:01:01.000000', '제목3', 0, 1);
insert into notice (contents, hits, likes, reg_date, title, deleted, user_id)
values ('내용', 0, 0, '2023-08-18 01:01:01.000000', '제목4', 0, 1);

insert into notice_like (notice_id, user_id)
values (3, 1)
     , (4, 1)
     , (1, 1)
     , (3, 2)
     , (1, 4)
     , (2, 4);