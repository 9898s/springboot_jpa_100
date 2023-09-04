package com.example.jpa.user.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.SignatureGenerationException;
import com.example.jpa.board.entity.Board;
import com.example.jpa.board.entity.BoardComment;
import com.example.jpa.board.model.ServiceResult;
import com.example.jpa.board.service.BoardService;
import com.example.jpa.common.exception.BizException;
import com.example.jpa.common.model.ResponseResult;
import com.example.jpa.notice.entity.Notice;
import com.example.jpa.notice.entity.NoticeLike;
import com.example.jpa.notice.model.NoticeReponse;
import com.example.jpa.notice.model.ResponseError;
import com.example.jpa.notice.repository.NoticeLikeRepository;
import com.example.jpa.notice.repository.NoticeRepository;
import com.example.jpa.user.entity.User;
import com.example.jpa.user.exception.ExistsEmailException;
import com.example.jpa.user.exception.PassowrdNotMatchException;
import com.example.jpa.user.exception.UserNotFoundException;
import com.example.jpa.user.model.*;
import com.example.jpa.user.repository.UserRepository;
import com.example.jpa.user.service.PointService;
import com.example.jpa.user.service.UserService;
import com.example.jpa.util.JWTUtils;
import com.example.jpa.util.PasswordUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
public class ApiUserController {
    private final UserRepository userRepository;
    private final NoticeRepository noticeRepository;
    private final NoticeLikeRepository noticeLikeRepository;

    private final BoardService boardService;
    private final PointService pointService;
    private final UserService userService;

//    @PostMapping("/api/user")
//    public ResponseEntity<?> addUser(@RequestBody @Valid UserInput userInput, Errors errors) {
//        List<ResponseError> responseErrorList = new ArrayList<>();
//
//        if (errors.hasErrors()) {
//            errors.getAllErrors().forEach(e -> responseErrorList.add(ResponseError.of((FieldError) e)));
//            return new ResponseEntity<>(responseErrorList, HttpStatus.BAD_REQUEST);
//        }
//        return ResponseEntity.ok().build();
//    }

//    @PostMapping("/api/user")
//    public ResponseEntity<?> addUser(@RequestBody @Valid UserInput userInput, Errors errors) {
//        List<ResponseError> responseErrorList = new ArrayList<>();
//
//        if (errors.hasErrors()) {
//            errors.getAllErrors().forEach(e -> responseErrorList.add(ResponseError.of((FieldError) e)));
//            return new ResponseEntity<>(responseErrorList, HttpStatus.BAD_REQUEST);
//        }
//
//        User user = User.builder()
//                .email(userInput.getEmail())
//                .userName(userInput.getUserName())
//                .password(userInput.getPassword())
//                .phone(userInput.getPhone())
//                .regDate(LocalDateTime.now())
//                .build();
//
//        userRepository.save(user);
//
//        return ResponseEntity.ok().build();
//    }

    @PutMapping("/api/user/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody @Valid UserUpdate userUpdate, Errors errors) {
        List<ResponseError> responseErrorList = new ArrayList<>();

        if (errors.hasErrors()) {
            errors.getAllErrors().forEach(e -> responseErrorList.add(ResponseError.of((FieldError) e)));
            return new ResponseEntity<>(responseErrorList, HttpStatus.BAD_REQUEST);
        }

        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("사용자 정보가 없습니다."));

        user.setPhone(userUpdate.getPhone());
        user.setUpdateDate(LocalDateTime.now());
        userRepository.save(user);

        return ResponseEntity.ok().build();
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<?> userNotFoundExceptionExceptionHandler(UserNotFoundException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/api/user/{id}")
    public UserResponse getUser(@PathVariable Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("사용자 정보가 없습니다."));

//        UserResponse userResponse = new UserResponse(user);
        return UserResponse.of(user);
    }

    @GetMapping("/api/user/{id}/notice")
    public List<NoticeReponse> userNotice(@PathVariable Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("사용자 정보가 없습니다."));

        List<Notice> noticeList = noticeRepository.findByUser(user);
        List<NoticeReponse> noticeReponseList = new ArrayList<>();
        noticeList.forEach(e -> noticeReponseList.add(NoticeReponse.of(e)));
        return noticeReponseList;
    }

//    @PostMapping("/api/user")
//    public ResponseEntity<?> addUser(@RequestBody @Valid UserInput userInput, Errors errors) {
//        List<ResponseError> responseErrorList = new ArrayList<>();
//        if (errors.hasErrors()) {
//            errors.getAllErrors().forEach(e -> responseErrorList.add(ResponseError.of((FieldError) e)));
//            return new ResponseEntity<>(responseErrorList, HttpStatus.BAD_REQUEST);
//        }
//
//        if (userRepository.countByEmail(userInput.getEmail()) > 0) {
//            throw new ExistsEmailException("이미 존재하는 이메일입니다.");
//        }
//
//        User user = User.builder()
//                .email(userInput.getEmail())
//                .userName(userInput.getUserName())
//                .phone(userInput.getPhone())
//                .password(userInput.getPassword())
//                .regDate(LocalDateTime.now())
//                .build();
//
//        userRepository.save(user);
//        return ResponseEntity.ok().build();
//    }

    @ExceptionHandler(value = {ExistsEmailException.class, PassowrdNotMatchException.class})
    public ResponseEntity<?> existsEmailExceptionHandler(RuntimeException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @PatchMapping("/api/user/{id}/password")
    public ResponseEntity<?> updateUserPassword(@PathVariable Long id, @RequestBody @Valid UserInputPassword userInputPassword, Errors errors) {
        List<ResponseError> responseErrorList = new ArrayList<>();
        if (errors.hasErrors()) {
            errors.getAllErrors().forEach(e -> responseErrorList.add(ResponseError.of((FieldError) e)));
            return new ResponseEntity<>(responseErrorList, HttpStatus.BAD_REQUEST);
        }

        User user = userRepository.findByIdAndPassword(id, userInputPassword.getPassword())
                .orElseThrow(() -> new PassowrdNotMatchException("비밀번호가 일치하지 않습니다."));

        user.setPassword(userInputPassword.getNewPassword());
        userRepository.save(user);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/api/user")
    public ResponseEntity<?> addUser(@RequestBody @Valid UserInput userInput, Errors errors) {
        List<ResponseError> responseErrorList = new ArrayList<>();
        if (errors.hasErrors()) {
            errors.getAllErrors().forEach(e -> responseErrorList.add(ResponseError.of((FieldError) e)));
            return new ResponseEntity<>(responseErrorList, HttpStatus.BAD_REQUEST);
        }

        if (userRepository.countByEmail(userInput.getEmail()) > 0) {
            throw new ExistsEmailException("이미 존재하는 이메일입니다.");
        }

        String encryptPassword = getEncryptPassword(userInput.getPassword());

        User user = User.builder()
                .email(userInput.getEmail())
                .userName(userInput.getUserName())
                .phone(userInput.getPhone())
                .password(encryptPassword)
                .regDate(LocalDateTime.now())
                .build();

        userRepository.save(user);
        return ResponseEntity.ok().build();
    }

    private String getEncryptPassword(String password) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder.encode(password);
    }

    @DeleteMapping("/api/user/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("사용자 정보가 없습니다."));

        try {
            userRepository.delete(user);
        } catch (DataIntegrityViolationException e) {
            String message = "제약 조건에 문제가 발생하였습니다.";
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            String message = "회원 탈퇴 중 문제가 발생하였습니다.";
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok().build();
    }

    @GetMapping("/api/user")
    public ResponseEntity<?> findUser(@RequestBody UserInputFind userInputFind) {
        User user = userRepository.findByUserNameAndPhone(userInputFind.getUserName(), userInputFind.getPhone())
                .orElseThrow(() -> new UserNotFoundException("사용자 정보가 없습니다."));

        UserResponse userResponse = UserResponse.of(user);
        return ResponseEntity.ok().body(userResponse);
    }

    @GetMapping("/api/user/{id}/password/reset")
    public ResponseEntity<?> resetUserPassword(@PathVariable Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("사용자 정보가 없습니다."));

        String resetPassword = getResetPassword();
        String resetEncryptPassword = getEncryptPassword(resetPassword);
        user.setPassword(resetEncryptPassword);
        userRepository.save(user);

        String message = String.format("[%s]님의 임시 비밀번호가 [%s]로 초기화 되었습니다.", user.getUserName(), resetPassword);
        sendSMS(message);
        return ResponseEntity.ok().build();
    }

    private String getResetPassword() {
        return UUID.randomUUID().toString().replaceAll("-", "").substring(0, 10);
    }

    private void sendSMS(String message) {
        System.out.println("[문자 메세지 전송]");
        System.out.println(message);
    }

    @GetMapping("/api/user/{id}/notice/like")
    public List<NoticeLike> likeNotice(@PathVariable Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("사용자 정보가 없습니다."));

        return noticeLikeRepository.findByUser(user);
    }

//    @PostMapping("/api/user/login")
//    public ResponseEntity<?> createToken(@RequestBody @Valid UserLogin userLogin, Errors errors) {
//        List<ResponseError> responseErrorList = new ArrayList<>();
//        if (errors.hasErrors()) {
//            errors.getAllErrors().forEach(e -> responseErrorList.add(ResponseError.of((FieldError) e)));
//            return new ResponseEntity<>(responseErrorList, HttpStatus.BAD_REQUEST);
//        }
//
//        User user = userRepository.findByEmail(userLogin.getEmail())
//                .orElseThrow(() -> new UserNotFoundException("사용자 정보가 없습니다."));
//
//        if (!PasswordUtils.equalsPassword(userLogin.getPassword(), user.getPassword())) {
//            throw new PassowrdNotMatchException("비밀번호가 일치하지 않습니다.");
//        }
//        return ResponseEntity.ok().build();
//    }

//    @PostMapping("/api/user/login")
//    public ResponseEntity<?> createToken(@RequestBody @Valid UserLogin userLogin, Errors errors) {
//        List<ResponseError> responseErrorList = new ArrayList<>();
//        if (errors.hasErrors()) {
//            errors.getAllErrors().forEach(e -> responseErrorList.add(ResponseError.of((FieldError) e)));
//            return new ResponseEntity<>(responseErrorList, HttpStatus.BAD_REQUEST);
//        }
//
//        User user = userRepository.findByEmail(userLogin.getEmail())
//                .orElseThrow(() -> new UserNotFoundException("사용자 정보가 없습니다."));
//
//        if (!PasswordUtils.equalsPassword(userLogin.getPassword(), user.getPassword())) {
//            throw new PassowrdNotMatchException("비밀번호가 일치하지 않습니다.");
//        }
//
//        String token = JWT.create()
//                .withExpiresAt(new Date())
//                .withClaim("user_id", user.getId())
//                .withSubject(user.getUserName())
//                .withIssuer(user.getEmail())
//                .sign(Algorithm.HMAC512("zerobase".getBytes()));
//
//        return ResponseEntity.ok().body(UserLoginToken.builder()
//                .token(token)
//                .build());
//    }

    @PostMapping("/api/user/login")
    public ResponseEntity<?> createToken(@RequestBody @Valid UserLogin userLogin, Errors errors) {
        List<ResponseError> responseErrorList = new ArrayList<>();
        if (errors.hasErrors()) {
            errors.getAllErrors().forEach(e -> responseErrorList.add(ResponseError.of((FieldError) e)));
            return new ResponseEntity<>(responseErrorList, HttpStatus.BAD_REQUEST);
        }

        User user = userRepository.findByEmail(userLogin.getEmail())
                .orElseThrow(() -> new UserNotFoundException("사용자 정보가 없습니다."));

        if (!PasswordUtils.equalsPassword(userLogin.getPassword(), user.getPassword())) {
            throw new PassowrdNotMatchException("비밀번호가 일치하지 않습니다.");
        }

        LocalDateTime expiredDateTime = LocalDateTime.now().plusMonths(1);
        Date expiredDate = Timestamp.valueOf(expiredDateTime);

        String token = JWT.create()
                .withExpiresAt(expiredDate)
                .withClaim("user_id", user.getId())
                .withSubject(user.getUserName())
                .withIssuer(user.getEmail())
                .sign(Algorithm.HMAC512("zerobase".getBytes()));

        return ResponseEntity.ok().body(UserLoginToken.builder()
                .token(token)
                .build());
    }

    @PatchMapping("/api/user/login")
    public ResponseEntity<?> refreshToken(HttpServletRequest request) {
        String token = request.getHeader("Z-TOKEN");
        String email = "";

        try {
            email = JWT.require(Algorithm.HMAC512("zerobase".getBytes()))
                    .build()
                    .verify(token)
                    .getIssuer();
        } catch (SignatureGenerationException e) {
            throw new PassowrdNotMatchException("비밀번호가 일치하지 않습니다.");
        }

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("사용자 정보가 없습니다."));

        LocalDateTime expiredDateTime = LocalDateTime.now().plusMonths(1);
        Date expiredDate = Timestamp.valueOf(expiredDateTime);

        String newToken = JWT.create()
                .withExpiresAt(expiredDate)
                .withClaim("user_id", user.getId())
                .withSubject(user.getUserName())
                .withIssuer(user.getEmail())
                .sign(Algorithm.HMAC512("zerobase".getBytes()));

        return ResponseEntity.ok().body(UserLoginToken.builder()
                .token(newToken)
                .build());
    }

    @DeleteMapping("/api/user/login")
    public ResponseEntity<?> removeToken(@RequestHeader("Z-TOKEN") String token) {
        String email = "";
        try {
            email = JWTUtils.getIssuer(token);
        } catch (SignatureGenerationException e) {
            return new ResponseEntity<>("토큰 정보가 정확하지 않습니다.", HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok().build();
    }

    @GetMapping("/api/user/board/post")
    public ResponseEntity<?> myPost(@RequestHeader("Z-TOKEN") String token) {
        String email = "";

        try {
            email = JWTUtils.getIssuer(token);
        } catch (SignatureGenerationException e) {
            return ResponseResult.fail("토큰 정보가 정확하지 않습니다.");
        }

        List<Board> list = boardService.postList(email);
        return ResponseResult.success(list);
    }

    @GetMapping("/api/user/board/comment")
    public ResponseEntity<?> myComments(@RequestHeader("Z-TOKEN") String token) {
        String email = "";

        try {
            email = JWTUtils.getIssuer(token);
        } catch (SignatureGenerationException e) {
            return ResponseResult.fail("토큰 정보가 정확하지 않습니다.");
        }

        List<BoardComment> list = boardService.commentList(email);
        return ResponseResult.success(list);
    }

    @PostMapping("/api/user/point")
    public ResponseEntity<?> userPoint(@RequestHeader("Z-TOKEN") String token, @RequestBody UserPointInput userPointInput) {
        String email = "";

        try {
            email = JWTUtils.getIssuer(token);
        } catch (SignatureGenerationException e) {
            return ResponseResult.fail("토큰 정보가 정확하지 않습니다.");
        }

        ServiceResult result = pointService.addPoint(email, userPointInput);
        return ResponseResult.result(result);
    }

    @PostMapping("/api/public/user")
    public ResponseEntity<?> addUser(@RequestBody UserInput userInput) {
        ServiceResult result = userService.addUser(userInput);
        return ResponseResult.result(result);
    }

    @PostMapping("/api/public/user/password/reset")
    public ResponseEntity<?> resetPassword(@RequestBody @Valid UserPasswordResetInput userPasswordResetInput, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseResult.fail("입력 값이 정확하지 않습니다.", ResponseError.of(errors.getAllErrors()));
        }

        ServiceResult result = null;
        try {
            result = userService.resetPassword(userPasswordResetInput);
        } catch (BizException e) {
            return ResponseResult.fail(e.getMessage());
        }
        return ResponseResult.result(result);
    }
}
