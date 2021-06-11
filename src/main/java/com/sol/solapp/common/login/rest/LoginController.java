package com.sol.solapp.common.login.rest;

import com.sol.solapp.common.auth.PrincipalDetails;
import com.sol.solapp.common.dto.LoginDTO;
import com.sol.solapp.user.entity.User;
import com.sol.solapp.user.rest.dto.UserDTO;
import com.sol.solapp.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

import static com.sol.solapp.common.JwtProperties.TOKEN_PREFIX;

@RestController
@RequestMapping("/rest/v1/login")
@Slf4j
@RequiredArgsConstructor
public class LoginController {

    private final UserService userService;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostMapping("/token")
    public String token() {
        return "token";
    }


    @GetMapping("/check")
    public ResponseEntity<LoginDTO> loginCheck(@AuthenticationPrincipal PrincipalDetails principalDetails) {
        System.out.println(principalDetails);
        LoginDTO loginDTO = LoginDTO.builder()
                .username(principalDetails.getUser().getUsername())
                .firstName(principalDetails.getUser().getFirstName())
                .lastName(principalDetails.getUser().getLastName())
                .email(principalDetails.getUser().getEmail())
                .smartThingsToken(principalDetails.getUser().getSmartThingsToken())
                .build();
        return ResponseEntity.ok(loginDTO);
    }

    @PostMapping("/join")
    public boolean joinUser(@RequestBody UserDTO dto) {
        System.out.println(dto);
        userService.joinUser(dto);
        return true;
    }

}
