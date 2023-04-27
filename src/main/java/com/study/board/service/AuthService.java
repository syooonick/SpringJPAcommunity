//package com.study.board.service;
//
//import com.study.board.entity.User;
//import com.study.board.repository.UserRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.stereotype.Service;
//
//import javax.servlet.http.HttpSession;
//import javax.transaction.Transactional;
//
//@RequiredArgsConstructor
//@Service
//public class AuthService {
//    private final UserRepository userRepository;
//    private final BCryptPasswordEncoder bCryptPasswordEncoder;
//
//    @Transactional
//    public User signup(User user) {
//        String rawPassword = user.getPassword();
//        String encPassword = bCryptPasswordEncoder.encode(rawPassword);
//        user.setPassword(encPassword);
//        user.setRole("ROLE_USER");
//
//        User userEntity = userRepository.save(user);
//        return userEntity;
//    }
//
//    public void logout(HttpSession session) {
//        session.invalidate();
//    }
//}
