package com.playtika.automation.school.test.framework.registration.client;

import com.playtika.automation.school.test.framework.registration.pojo.requests.RegisterRequest;
import com.playtika.automation.school.test.framework.registration.pojo.responses.RegisterResponse;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(
        name = "register-client",
        url = ("${app.host}")
)
public interface RegisterFeignClient {
    @PostMapping(path = "/v1/accounts")
    RegisterResponse register(@RequestBody RegisterRequest request);
}