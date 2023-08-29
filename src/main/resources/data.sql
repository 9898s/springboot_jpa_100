insert into user (email, password, phone, reg_date, update_date, user_name, status, lock_yn)
values ('test1@test.com', '1111', '010-1111-1111', '2023-08-23 00:00:00.000000', null, '홍길동', 1, 0)
     , ('test2@test.com', '2222', '010-2222-2222', '2023-08-23 00:00:00.000000', null, '손흥민', 1, 0)
     , ('test3@test.com', '3333', '010-3333-3333', now(), null, '봉준호', 1, 0)
     , ('test4@test.com', '4444', '010-4444-4444', now(), null, '박재범', 2, 0);

insert into notice (contents, hits, likes, reg_date, title, deleted, user_id)
values ('내용', 0, 0, '2023-08-18 01:01:01.000000', '제목1', 0, 1)
     , ('내용', 0, 0, '2023-08-18 01:01:01.000000', '제목2', 0, 1)
     , ('내용', 0, 0, '2023-08-18 01:01:01.000000', '제목3', 0, 1)
     , ('내용', 0, 0, '2023-08-18 01:01:01.000000', '제목4', 0, 1);

insert into notice_like (notice_id, user_id)
values (3, 1)
     , (4, 1)
     , (1, 1)
     , (3, 2)
     , (1, 4)
     , (2, 4);

insert into board_type (board_name, reg_date, using_yn)
values ('게시판1', now(), 1)
     , ('게시판2', now(), 1);

insert into board (board_type_id, user_id, top_yn, title, contents, reg_date)
values (1, 1, 0, '게시글1', '게시글내용1', now())
     , (1, 1, 0, '게시글2', '게시글내용2', now());

insert into board_comment(comments, reg_date, board_id, user_id)
values ('게시글 1번에 대한 댓글1', now(), 1, 1)
     , ('게시글 1번에 대한 댓글2', now(), 1, 1)
     , ('게시글 1번에 대한 댓글2', now(), 2, 1);