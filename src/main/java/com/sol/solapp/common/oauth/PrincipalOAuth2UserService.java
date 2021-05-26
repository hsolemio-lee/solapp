package com.sol.solapp.common.oauth;

import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
public class PrincipalOAuth2UserService extends DefaultOAuth2UserService {

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        System.out.println("userReqeust: "+userRequest.getClientRegistration());
        System.out.println("userReqeust: "+userRequest.getAccessToken());
        // 구글로그인 버튼 클릭 -> 구글 로그인 창 -> 로그인 완료 -> code 리턴 -> AccessToken 요청
        // userRequest 정보 -> 회원 프로필 받아야함(loadUser 함수)
        System.out.println("userReqeust: "+super.loadUser(userRequest).getAttributes());
        OAuth2User oAuth2User = super.loadUser(userRequest);

        return super.loadUser(userRequest);
    }
}
