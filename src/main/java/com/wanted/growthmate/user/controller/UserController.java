package com.wanted.growthmate.user.controller;


import com.wanted.growthmate.user.dto.UserDTO;
import com.wanted.growthmate.user.entity.User;
import com.wanted.growthmate.user.exception.UserNotFoundByIdException;
import com.wanted.growthmate.user.exception.UserWrongPasswordException;
import com.wanted.growthmate.user.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signup")
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) {

        UserDTO createdDTO = userService.signUp(userDTO);

        return ResponseEntity.ok(createdDTO);
    }

    @PostMapping("/login")
    public ResponseEntity<UserDTO> login(@RequestBody UserDTO userDTO, HttpSession session) {

        UserDTO loginUser = userService.login(userDTO);

        session.setAttribute("loginUserId", loginUser.getId());
        session.setMaxInactiveInterval(3600);//세션 만료 1시간

        return ResponseEntity.ok(loginUser);
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpSession session){

         if(session != null){
             session.invalidate();
         }
        return ResponseEntity.ok("로그아웃 되었습니다.");
    }

    @GetMapping("/{id}")
    public UserDTO findUserByIds(@PathVariable ("id") Long id){

        return  userService.findUserById(id);
    }

    @PatchMapping("{id}")
    public ResponseEntity<UserDTO> updateUser(
            @PathVariable ("id") Long id
            ,@RequestBody UserDTO userDTO) {

        UserDTO updatedDTO = userService.updateUser(id, userDTO);

        return ResponseEntity.ok(updatedDTO);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<UserDTO> deleteUser(@PathVariable ("id") Long id){

        userService.deleteById(id);

        return ResponseEntity.ok().build();
    }


}
