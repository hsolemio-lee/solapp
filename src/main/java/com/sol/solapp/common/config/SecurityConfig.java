package com.sol.solapp.common.config;

import com.sol.solapp.common.oauth.PrincipalOAuth2UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity // 스프링 시큐리티 필터가 스프링 필터체인에 등록됨
@EnableGlobalMethodSecurity(securedEnabled = true) // secured 어노테이션 사용 가능
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final PrincipalOAuth2UserService oauth2UserService;

    @Bean
    public BCryptPasswordEncoder encodePassword() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/h2/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeRequests()
                .antMatchers("/h2/**").permitAll()
                .antMatchers("/loginForm").permitAll()
                .antMatchers("/joinForm").permitAll()
                .antMatchers("/login").permitAll()
                .antMatchers("/join").permitAll()
                .antMatchers("/logout").permitAll()
                .antMatchers("/oauth2/authorization/google").permitAll()
                .antMatchers(HttpMethod.POST, "/rest/v1/users").authenticated()
                .antMatchers(HttpMethod.GET, "/rest/v1/users").access("hasRole('ROLE_USER')")
                .antMatchers("/**").authenticated()
                .and()
                .formLogin()
                .loginPage("/loginForm")
                .loginProcessingUrl("/login") //login 주소가 호출이 되면 시큐리티가 낚아채서 대신 로그인함.
                .defaultSuccessUrl("/")
                .and()
                .oauth2Login()
                .loginPage("/loginForm")
                // 구글 로그인이 완료된 뒤의 후처리 필요.
                // 1. 코드받기(인증)
                // 2. 액세스토큰(권한)
                // 3. 사용자프로필 정보 겟
                // 4-1. 그 정보 토대로 회원가입 진행
                // 4-2. 추가정보 입력 회원가입 진행
                // 이러한 프로세스를 oauth2 client가 다 해줌
                .userInfoEndpoint()
                .userService(oauth2UserService)
        ;
    }
}
