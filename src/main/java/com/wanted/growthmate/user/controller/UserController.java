package com.wanted.growthmate.user.controller;


import com.wanted.growthmate.user.dto.UserDTO;
import com.wanted.growthmate.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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
