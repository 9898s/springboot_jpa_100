insert into user (email, password, phone, reg_date, update_date, user_name, status, lock_yn, password_reset_yn)
values ('sueh9898@naver.com', '1111', '010-1111-1111', '2023-08-23 00:00:00.000000', null, '홍길동', 1, 0, 0)
     , ('suhwan@kakao.com', '2222', '010-2222-2222', '2023-08-23 00:00:00.000000', null, '손흥민', 1, 0, 0)
     , ('test3@test.com', '3333', '010-3333-3333', now(), null, '봉준호', 1, 0, 0)
     , ('test4@test.com', '4444', '010-4444-4444', now(), null, '박재범', 2, 0, 0);

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
     , ('게시판2', now(), 1)
     , ('문의게시판', now(), 1);

insert into board (board_type_id, user_id, top_yn, title, contents, reg_date)
values (1, 1, 0, '게시글1', '게시글내용1', now())
     , (1, 1, 0, '게시글2', '게시글내용2', now())
     , (3, 1, 0, '문의제목1', '문의내용1', now());

insert into board_comment(comments, reg_date, board_id, user_id)
values ('게시글 1번에 대한 댓글1', now(), 1, 1)
     , ('게시글 1번에 대한 댓글2', now(), 1, 1)
     , ('게시글 1번에 대한 댓글2', now(), 2, 1);


insert into mail_template(template_id, title, contents, send_email, send_user_name, reg_date)
values ('USER_RESET_PASSWORD',
        '{USER_NAME}님의 비밀번호 초기화 요청입니다.',
        '<div><p>{USER_NAME}님 안녕하세요.</p><p>아래 링크를 클릭하여, 비밀번호를 초기화해 주세요.</p><p><a href="{SERVER_URL}/reset?key={RESET_PASSWORD_KEY}">초기화</a></p></div>',
        'sueh9898@gmail.com', '관리자', now()),

       ('BOARD_ADD',
        '{USER_NAME}님이 글을 게시하였습니다.',
        '<div><p>제목: {BOARD_TITLE}</p><p>내용</p><div>{BOARD_CONTENTS}</div></div>',
        'sueh9898@gmail.com', '관리자', now()),

       ('BOARD_REPLY',
        '{USER_NAME}님이 글에 답변을 하였습니다.',
        '<div><p>제목: {BOARD_TITLE}</p><p>내용</p><div>{BOARD_CONTENTS}</div><p>답변</p><div>{BOARD_REPLY_CONTENTS}</div></div>',
        'sueh9898@gmail.com', '관리자', now()),

       ('USER_SERVICE_NOTICE',
        '{USER_NAME}님 안녕하세요',
        '<div><p>개인정보 이용 내역 안내</p><p>서비스를 잘 이용하고 계십니다.</p></div>',
        'sueh9898@gmail.com', '관리자', now());

insert into logs(text, reg_date)
values ('로그1', now()),
       ('로그2', now()),
       ('로그3', now()),
       ('로그4', now()),
       ('로그5', now());