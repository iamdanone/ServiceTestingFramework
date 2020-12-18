package com.playtika.automation.school.test.framework.login.action;

import com.playtika.automation.school.test.framework.login.client.AuthFeignClient;
import com.playtika.automation.school.test.framework.login.pojo.requests.AuthenticateRequest;
import com.playtika.automation.school.test.framework.login.pojo.responses.AuthenticateResponse;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AuthActions {

    private final AuthFeignClient authFeignClient;
    private final String grantType;
    private final String authToken;

    public AuthenticateResponse authorization(String userName, String password) {
        return authFeignClient.login(authToken, grantType, userName, password);
    }
}