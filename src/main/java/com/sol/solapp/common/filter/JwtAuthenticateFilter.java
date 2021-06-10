package com.sol.solapp.common.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sol.solapp.common.auth.PrincipalDetails;
import com.sol.solapp.user.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

import static com.sol.solapp.common.JwtProperties.*;

@Slf4j
@RequiredArgsConstructor
public class JwtAuthenticateFilter extends UsernamePasswordAuthenticationFilter {

    @Value("${jwt.token.code}")
    private String JWT_CODE;


    private final AuthenticationManager authenticationManager;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        log.debug("Try login...");

        // 1. username, password
        ObjectMapper om = new ObjectMapper();
        try {
            User user = om.readValue(request.getInputStream(), User.class);
            System.out.println(user);

            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());

            // 2. 정상인지 로그인시도, PrincipalDetailsService가 호출 loadUserByName() 실행됨
            Authentication authentication = authenticationManager.authenticate(authenticationToken);

            return authentication;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }


    // attempAuthentication 실행 후 인증 완료되면 successfullAuthentication 함수 실행
    // JWT 토큰을 만들어서 request 요청한 사용자에게 JWT 토큰을 response 해주면 됨
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        log.debug("Authentication Success!!!");
        log.debug("==============JWT_CODE: "+JWT_CODE);
        PrincipalDetails principalDetails = (PrincipalDetails) authResult.getPrincipal();

        String jwtToken = JWT.create()
                .withSubject("soltoken")
                .withExpiresAt(new Date(System.currentTimeMillis()+EXPIRE_TIME))
                .withClaim("username", principalDetails.getUser().getUsername())
                .withClaim("password", principalDetails.getUser().getPassword())
                .sign(Algorithm.HMAC512(JWT_CODE));

        response.addHeader(AUTH_HEADER, TOKEN_PREFIX +jwtToken);
    }
}
