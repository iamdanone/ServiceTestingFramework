package com.playtika.automation.school.test.framework.notes.pojo.requests;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class DeleteNoteByIdRequest {

    String id;
    String auth;
}
