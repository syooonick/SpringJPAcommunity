package com.study.board.service;

import com.study.board.UserCreateForm;
import com.study.board.entity.Board;
import com.study.board.entity.User;
import com.study.board.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

//@RequiredArgsConstructor
@Service
public class UserService {
        private final UserRepository userRepository;

        public User create(String username, String email, String password) {

            User user = new User();
            user.setUsername(username);
            user.setEmail(email);
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            user.setPassword(passwordEncoder.encode(password));

            this.userRepository.save(user);
            return user;
        }

        public UserService(UserRepository userRepository) {
            this.userRepository = userRepository;
        }
}
