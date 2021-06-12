package com.sol.solapp.common.config;

import com.sol.solapp.common.filter.CorsFilter;
import com.sol.solapp.common.filter.JwtAuthenticateFilter;
import com.sol.solapp.common.filter.JwtAuthorizationFilter;
import com.sol.solapp.common.oauth.PrincipalOAuth2UserService;
import com.sol.solapp.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableWebSecurity // 스프링 시큐리티 필터가 스프링 필터체인에 등록됨
@EnableGlobalMethodSecurity(securedEnabled = true) // secured 어노테이션 사용 가능
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final PrincipalOAuth2UserService oauth2UserService;

    private final CorsFilter corsFilter;

    private final UserRepository userRepository;

    private final Environment env;

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
//        http.addFilterBefore(corsFilter, CsrfFilter.class);
        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .formLogin().disable()
                .httpBasic().disable()
                .cors().configurationSource(corsConfigurationSource())
                .and()
                .addFilter(new JwtAuthenticateFilter(authenticationManager(), env))
                .addFilter(new JwtAuthorizationFilter(authenticationManager(), userRepository, env))
                .authorizeRequests()
                .antMatchers("/rest/v1/login/join")
                .permitAll()
                .antMatchers("/rest/v1/**")
                .access("hasRole('ROLE_USER')")
                .anyRequest().permitAll();

    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();

        configuration.setAllowedOrigins(Arrays.asList("http://localhost:9090", "http://192.168.0.10:8080", "http://hansolo.iptime.org", "http://hansolo.iptime.org:8080", "http://192.168.0.7:9090", "http://hansolo.iptime.org:9090"));
        configuration.setAllowedHeaders(Arrays.asList("x-requested-with", "origin", "content-type", "accept", "x-xsrf-token", "pd-super-key", "authorization"));
        configuration.setAllowedMethods(Arrays.asList("POST", "GET", "PUT", "DELETE", "PATCH", "OPTIONS"));
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
