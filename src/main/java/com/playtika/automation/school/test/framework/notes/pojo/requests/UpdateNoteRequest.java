package com.playtika.automation.school.test.framework.notes.pojo.requests;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class UpdateNoteRequest {

    String content;
    Integer version;
}
