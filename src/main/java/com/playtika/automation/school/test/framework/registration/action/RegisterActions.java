package com.playtika.automation.school.test.framework.registration.action;

import com.playtika.automation.school.test.framework.registration.client.RegisterFeignClient;
import com.playtika.automation.school.test.framework.registration.pojo.requests.RegisterRequest;
import com.playtika.automation.school.test.framework.registration.pojo.responses.RegisterResponse;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class RegisterActions {

    private final RegisterFeignClient registerFeignClient;

    public RegisterResponse registerUser(String email, String password) {
        RegisterRequest registerRequest = RegisterRequest.builder().email(email).password(password).build();
        return registerFeignClient.register(registerRequest);
    }
}