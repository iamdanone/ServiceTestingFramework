package com.playtika.automation.school.test.framework.notes.pojo.responses;

import lombok.Value;

@Value
public class GetNoteByIdResponse {

    String id;
    String content;
    Integer version;
}