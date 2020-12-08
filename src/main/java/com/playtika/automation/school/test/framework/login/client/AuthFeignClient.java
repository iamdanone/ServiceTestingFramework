package com.playtika.automation.school.test.framework.login.client;

import com.playtika.automation.school.test.framework.login.pojo.responses.AuthenticateResponse;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(
        name = "auth-client",
        url = ("${app.host}")
)
public interface AuthFeignClient {

    @PostMapping(path = "/oauth/token", consumes = "application/x-www-form-urlencoded")
    AuthenticateResponse login(@RequestHeader("Authorization") String authorization,
                               @RequestParam("grant_type") String grantType,
                               @RequestParam("username") String userName,
                               @RequestParam("password") String password);
}