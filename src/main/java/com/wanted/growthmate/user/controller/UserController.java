package com.wanted.growthmate.user.controller;


import com.wanted.growthmate.user.dto.UserDTO;
import com.wanted.growthmate.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
