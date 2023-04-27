//package com.study.board.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//
//
//@Configuration
//@EnableWebSecurity // 시큐리티 활성화
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//
//    @Bean
//    public BCryptPasswordEncoder encoder() {
//        // DB 패스워드 암호화
//        return new BCryptPasswordEncoder();
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        super.configure(http); // 삭제시 기존 시큐리티가 가진 기능 비활성화
//        http.csrf().disable(); // csrf 토큰 비활성화
//
//        http.authorizeRequests()
//                .antMatchers("/", "/main/**").authenticated() // 메인으로 시작시 인증이 필요
//                .anyRequest().permitAll() // 아닌 주소는 인증 필요없음
//                .and()
//                .formLogin()
//                .loginPage("/signin") // 인증 필요한 주소로 이동
//                .loginProcessingUrl("/signin") // 스프링 시큐리티가 로그인 자동 진행 POST 방식 로그인 진행
//                .defaultSuccessUrl("/main"); // 로그인이 정삭정이면 "/"로 이동
//    }
//}
