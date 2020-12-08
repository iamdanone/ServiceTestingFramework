package com.playtika.automation.school.test.framework.registration.pojo.requests;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class RegisterRequest {

    String email;
    String password;
}