package com.team.pretLancer_7.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Security 설정
 */
// 환경설정을 하는 클래스야 !
@Configuration
public class WebSecurityConfig {
    @Autowired
    private DataSource dataSource;

    //설정
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
        .authorizeRequests()
        .mvcMatchers("/","/email").permitAll()
        .antMatchers("/",
                "/email",
                "/confirm",
        		"/member/join",
        		"/member/joinForm",
        		"/member/idcheck").permitAll()
                .antMatchers(
                "/image/**",
        		"/assets/**",
        		"/image/**").permitAll()
        .antMatchers("/admin").hasRole("ADMIN")
        .antMatchers("/translate/**").hasAnyRole("TRANSLATOR","ADMIN")
        .anyRequest().authenticated()
        .and()
        .formLogin()						//일반적인 폼을 이용한 로그인 처리/실패 방법을 사용
        .loginPage("/member/login")		//시큐리티에서 제공하는 기본 폼이 아닌 사용자가 만든 폼 사용
        .loginProcessingUrl("/member/login").permitAll()	//인증 처리를 하는 URL을 설정. 로그인 폼의 action으로 지정
        .usernameParameter("memberid")		//로그인폼의 아이디 입력란의 name
        .passwordParameter("memberpw")		//로그인폼의 비밀번호 입력란의 name
        .and()
        .logout()
        .logoutUrl("/member/logout")		//로그아웃 처리 URL
        .logoutSuccessUrl("/public").permitAll()	//로그아웃시에 이동할 경로
        .and()
        .cors()
        .and()
        .csrf().ignoringAntMatchers("/email")
        .and()
        .httpBasic();
        return http.build();
    }

    
    


    //인증을 위한 쿼리
    @Autowired
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
        .dataSource(dataSource)
        // 인증 (로그인)
        .usersByUsernameQuery(
        		"select memberid username, memberpw password, enabled " +
                "from pret_member " +
                "where memberid = ?")

        // 권한
        .authoritiesByUsernameQuery(
        		"select memberid username, role_name role_name " +
                "from pret_member " +
                "where memberid = ?");
    }

    // 단방향 비밀번호 암호화
    // 패스워드 인코더 오토와이어드로 서비스와 연결
    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}
