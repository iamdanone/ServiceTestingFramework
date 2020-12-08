package com.playtika.automation.school.test.framework.notes.client;

import java.util.List;

import com.playtika.automation.school.test.framework.notes.pojo.requests.CreateNoteRequest;
import com.playtika.automation.school.test.framework.notes.pojo.requests.UpdateNoteRequest;
import com.playtika.automation.school.test.framework.notes.pojo.responses.CreateNoteResponse;
import com.playtika.automation.school.test.framework.notes.pojo.responses.DeleteNoteByIdResponse;
import com.playtika.automation.school.test.framework.notes.pojo.responses.GetNoteByIdResponse;
import com.playtika.automation.school.test.framework.notes.pojo.responses.GetUserNotesResponse;
import com.playtika.automation.school.test.framework.notes.pojo.responses.UpdateNoteResponse;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(
        name = "note-client",
        url = "https://taschool-notes-service.herokuapp.com"
)
public interface NotesFeignClient {

    @PostMapping(path = "v1/notes")
    CreateNoteResponse createNote(@RequestHeader("authorization") String authorization,
                                  @RequestBody CreateNoteRequest content);

    @GetMapping(path = "v1/notes")
    List<GetUserNotesResponse> getAllNotes(@RequestHeader("authorization") String authorization);

    @GetMapping(path = "v1/notes/{id}")
    GetNoteByIdResponse getNoteById(@RequestHeader("authorization") String authorization,
                                    @RequestParam("id") Integer id);

    @PutMapping(path = "v1/notes/{id}")
    UpdateNoteResponse updateNote(@RequestHeader("authorization") String authorization,
                                  @RequestParam("id") Integer id,
                                  @RequestBody UpdateNoteRequest content);

    @DeleteMapping(path = "v1/notes/{id}")
    DeleteNoteByIdResponse deleteNoteById(@RequestHeader("authorization") String authorization,
                                          @RequestParam("id") Integer id);
}
