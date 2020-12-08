package com.playtika.automation.school.test.framework.notes.pojo.requests;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class CreateNoteRequest {

    String content;
}
