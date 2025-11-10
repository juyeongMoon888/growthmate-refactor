package com.wanted.growthmate.user.service;


import com.wanted.growthmate.user.dto.UserDTO;
import com.wanted.growthmate.user.entity.User;
import com.wanted.growthmate.user.exception.UserNotFoundByIdException;
import com.wanted.growthmate.user.repository.UserRepository;
import com.wanted.growthmate.user.role.Role;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    //회원 가입
    public UserDTO signUp(UserDTO userDTO) {

        User user = new User(userDTO.getUsername()
                ,userDTO.getEmail(), userDTO.getPassword()
                ,Role.ROLE_STUDENT, LocalDateTime.now());


        User signUp = userRepository.save(user);

        return new UserDTO(signUp);
    }
    //ID 조회
    public UserDTO findUserById(Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(()->new UserNotFoundByIdException("해당아이디를 찾을 수 없습니다." + userId));


        return new UserDTO(user);
    }
    //ID 변경
    @Transactional
    public UserDTO updateUser(Long userId, UserDTO userDTO) {

        User user = userRepository.findById(userId)
                .orElseThrow(()-> new UserNotFoundByIdException("id 없음"));

        user.setUsername(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());

        return new UserDTO(user);

    }
    //ID 삭제
    public void deleteById(Long userId) {
        userRepository.deleteById(userId);
    }



}
