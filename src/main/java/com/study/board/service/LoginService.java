//package com.study.board.service;
//
//import com.study.board.entity.User;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//
//@Controller
//@RequiredArgsConstructor
//@Slf4j
//
//public class LoginService {
//
//    @Autowired
//    private UserRepository userRepository;
//
//    public boolean login(User user) {
//
//        User findUser = userRepository.findByUserId(user.getUserId());
//
//        if(findUser == null) {
//            return false;
//
//        }
//
//        if(!findUser.getPassword().equals(user.getPassWord())){
//            return false;
//        }
//        return true;
//    }
//}
