package com.wanted.growthmate.user.service;

import com.wanted.growthmate.user.dto.UserDTO;
import com.wanted.growthmate.user.entity.User;
import com.wanted.growthmate.user.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceTest {

    @Autowired
    UserService userService;
    UserRepository userRepository;

    @Test
    void signUp() {
        // 1. given
        User user = new User();
        user.setUsername("wanted");
        user.setPassword("1234");
        user.setEmail("www@gmailcom");

        // 2. when
        UserDTO userDTO = userService.signUp(new UserDTO(user));

        // 3. then
        Assertions.assertNotNull(userDTO, "성공했습니다.");

    }

    @Test
    void findUserById() {

        UserDTO testDto = new UserDTO();
        testDto.setUsername("testUser");
        testDto.setPassword("testPass123");
        testDto.setEmail("test@email.com");

        UserDTO signUpDTO = userService.signUp(testDto);

        UserDTO foundUserDto = userService.findUserById(signUpDTO.getId());

        assertNotNull(foundUserDto);
        assertEquals(testDto.getUsername(), foundUserDto.getUsername());
    }

    @Test
    void updateUser() {
        UserDTO savedUser = userService.signUp(new UserDTO());
        Long userId = savedUser.getId();

        UserDTO updateRequest = new UserDTO();
        updateRequest.setPassword("newPass456"); // 바꿀 비번
        updateRequest.setEmail("new@email.com"); // 바꿀 이메일

        UserDTO updatedUser = userService.updateUser(userId, updateRequest);

        assertEquals("new@email.com", updatedUser.getEmail());

        User userInDb = userRepository.findById(userId).get();
        assertEquals("new@email.com", userInDb.getEmail());
    }

    @Test
    @Transactional
    void deleteById() {
        // --- Given (준비) ---
        UserDTO savedUser = userService.signUp(new UserDTO());
        Long userId = savedUser.getId(); // (UserDTO에 id가 추가되었다고 가정!)

        userService.deleteById(userId);


        boolean isPresent = userRepository.findById(userId).isPresent();
        assertFalse(isPresent, "삭제된 유저는 DB에 없어야 합니다.");
    }
}