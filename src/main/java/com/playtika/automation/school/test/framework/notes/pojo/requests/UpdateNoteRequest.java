package com.playtika.automation.school.test.framework.notes.pojo.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class UpdateNoteRequest {
    @JsonProperty("content")
    String content;
    @JsonProperty("version")
    Integer version;
}
