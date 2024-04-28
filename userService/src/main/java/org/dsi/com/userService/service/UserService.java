package org.dsi.com.userService.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dsi.com.userService.model.User;
import org.dsi.com.userService.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    final UserRepository userRepository;
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User saveUser(User user) {

        String uuidi= UUID.randomUUID().toString();
        User newuser = User.builder()
                .userName(uuidi)
                .firstName("Abdullah")
                .lastName("Baki")
                .password("1232")
                .email( uuidi+"@gmail.com")
                .build();
        return  userRepository.save(newuser);

    }
}
