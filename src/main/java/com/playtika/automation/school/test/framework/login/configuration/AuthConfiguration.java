package com.playtika.automation.school.test.framework.login.configuration;

import com.playtika.automation.school.test.framework.login.action.AuthActions;
import com.playtika.automation.school.test.framework.login.client.AuthFeignClient;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@EnableFeignClients(clients = AuthFeignClient.class)
@Configuration
@EnableAutoConfiguration
public class AuthConfiguration {

    @Value("${grant.type}")
    private String grantType;
    @Value("${auth.token}")
    private String authToken;

    @Bean
    public AuthActions authActions(AuthFeignClient client) {
        return new AuthActions(client, grantType, authToken);
    }
}

