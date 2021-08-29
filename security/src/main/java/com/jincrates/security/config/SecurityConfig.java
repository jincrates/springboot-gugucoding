package com.jincrates.security.config;

import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@Log4j2
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    PasswordEncoder passwordEncoder() {
        //BCryptPasswordEncoder: bcrypt라는 해시 함수를 이용해서 패스워드를 암호화하는 목적으로 설계된 클래스
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // permitAll() : 모든 사용자에게 허락한다는 의미로 로그인을 하지 않은 사용자도 익명의 사용자로 간주되어 접근이 가능함
        http.authorizeRequests()
                .antMatchers("/sample/all").permitAll()
                .antMatchers("/sample/member").hasRole("USER");

        http.formLogin();  //권한 인증에 문제시 로그인 화면 이동
        http.csrf().disable();  // csrf 토큰 비활성화
        http.logout();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //사용자 계정은 user1
        auth.inMemoryAuthentication().withUser("user1")
                .password("$2a$10$OzFhtpo9AslJVsliNXaX1OKy/Lgk3I0eurnB1guyP9mtMuhOlPBta")  //1111 패스워드 인코딩 결과
                .roles("USER");
    }
}
