package com.wanted.growthmate.user.controller;


import com.wanted.growthmate.user.dto.UserDTO;
import com.wanted.growthmate.user.entity.User;
import com.wanted.growthmate.user.exception.UserNotFoundByIdException;
import com.wanted.growthmate.user.exception.UserWrongPasswordException;
import com.wanted.growthmate.user.interceptor.AuthRequired;
import com.wanted.growthmate.user.role.Role;
import com.wanted.growthmate.user.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) {

        UserDTO createdDTO = userService.signUp(userDTO);

        return ResponseEntity.ok(createdDTO);
    }

    @PostMapping("/login")
    public ResponseEntity<UserDTO> login(@RequestBody UserDTO userDTO, HttpSession session) {

        UserDTO loginUser = userService.login(userDTO);

        session.setAttribute("loginUserId", loginUser.getId());
        session.setAttribute("loginUserRole", loginUser.getRole());
        session.setMaxInactiveInterval(3600);//세션 만료 1시간

        return ResponseEntity.ok(loginUser);
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpSession session) {

        if (session != null) {
            session.invalidate();
        }
        return ResponseEntity.ok("로그아웃 되었습니다.");
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO>
    findUserByIds(
            @PathVariable("id") Long id,
            HttpSession session) {

        //본인 확인
        Long loginUserId = (Long) session.getAttribute("loginUserId");
        Role loginUserRole = (Role) session.getAttribute("loginUserRole");

        if (!loginUserId.equals(id) && loginUserRole != Role.ROLE_ADMIN) {
            // 403 Forbidden (권한 없음) 반환
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
        }

        // 본인 확인
        UserDTO user = userService.findUserById(id);
        return ResponseEntity.ok(user);
    }

    @PatchMapping("{id}")
    public ResponseEntity<UserDTO> updateUser(
            @PathVariable("id") Long id
            , @RequestBody UserDTO userDTO
            , HttpSession session) {

        Long loginUserId = (Long) session.getAttribute("loginUserId");
        Role loginUserRole = (Role) session.getAttribute("loginUserRole");

        if (!loginUserId.equals(id) && loginUserRole != Role.ROLE_ADMIN) {

            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
        }

            UserDTO updatedDTO = userService.updateUser(id, userDTO);

            return ResponseEntity.ok(updatedDTO);
        }


        @AuthRequired(role = Role.ROLE_ADMIN)
        @DeleteMapping("{id}")
        public ResponseEntity<UserDTO> deleteUser (@PathVariable("id") Long id){

            userService.deleteById(id);

            return ResponseEntity.ok().build();
        }
    //회원 본인이 탈퇴
    @DeleteMapping("/withdraw")
    public ResponseEntity<String> withdrawAccount(HttpSession session) {

        // 1. 세션에서 "본인" ID를 가져옵니다.
        Long loginUserId = (Long) session.getAttribute("loginUserId");


        if (loginUserId == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("로그인이 필요합니다.");
        }

        // 2. "본인" ID로 DB에서 삭제
        userService.deleteById(loginUserId);

        // 3. 세션을 만료시켜 즉시 로그아웃 처리
        session.invalidate();

        return ResponseEntity.ok("회원 탈퇴가 완료되었습니다.");
    }


    }

