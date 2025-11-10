package com.wanted.growthmate.user.service;


import com.wanted.growthmate.user.dto.UserDTO;
import com.wanted.growthmate.user.entity.User;
import com.wanted.growthmate.user.repository.UserRepository;
import com.wanted.growthmate.user.role.Role;
import jakarta.persistence.EnumType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;



@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserDTO signUp(UserDTO userDTO) {

        User user = new User(userDTO.getUsername()
                ,userDTO.getEmail(), userDTO.getPassword()
                ,Role.ROLE_STUDENT, LocalDateTime.now());


        User signUp = userRepository.save(user);

        return new UserDTO(signUp);




    }



}
