package com.sol.solapp.smartthings.feign.config;

import com.sol.solapp.user.service.UserService;
import feign.RequestInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
@RequiredArgsConstructor
public class SmartThingsClientConfiguration {

    private final Environment env;

    @Bean
    RequestInterceptor requestInterceptor() {
        return requestTemplate -> requestTemplate.header("Authorization", "Bearer "+env.getProperty("smartthings.api.auth.token"));
    }
}
