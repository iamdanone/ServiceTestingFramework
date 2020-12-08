package com.playtika.automation.school.test.framework.notes.pojo.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

@Value
public class UpdateNoteResponse {
    @JsonProperty("id")
    Integer id;
    @JsonProperty("content")
    String content;
    @JsonProperty("version")
    Integer version;
}
