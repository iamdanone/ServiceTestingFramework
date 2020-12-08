package com.playtika.automation.school.test.framework.registration.configuration;

import com.playtika.automation.school.test.framework.registration.action.RegisterActions;
import com.playtika.automation.school.test.framework.registration.client.RegisterFeignClient;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@EnableFeignClients(clients = RegisterFeignClient.class)
@Configuration
@EnableAutoConfiguration
public class RegisterConfiguration {

    @Bean
    public RegisterActions registerActions(RegisterFeignClient client) {
        return new RegisterActions(client);
    }
}
