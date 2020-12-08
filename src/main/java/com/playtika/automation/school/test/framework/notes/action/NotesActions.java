package com.playtika.automation.school.test.framework.notes.action;

import java.util.List;

import com.playtika.automation.school.test.framework.notes.client.NotesFeignClient;
import com.playtika.automation.school.test.framework.notes.pojo.requests.CreateNoteRequest;
import com.playtika.automation.school.test.framework.notes.pojo.requests.UpdateNoteRequest;
import com.playtika.automation.school.test.framework.notes.pojo.responses.CreateNoteResponse;
import com.playtika.automation.school.test.framework.notes.pojo.responses.DeleteNoteByIdResponse;
import com.playtika.automation.school.test.framework.notes.pojo.responses.GetNoteByIdResponse;
import com.playtika.automation.school.test.framework.notes.pojo.responses.GetUserNotesResponse;
import com.playtika.automation.school.test.framework.notes.pojo.responses.UpdateNoteResponse;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class NotesActions {

    private final NotesFeignClient notesFeignClient;

    public CreateNoteResponse createNote(String auth, String firstContent) {
        CreateNoteRequest createNoteRequest = CreateNoteRequest.builder().content(firstContent).build();
        System.out.println(createNoteRequest);
        return notesFeignClient.createNote(auth, createNoteRequest);
    }

    public List<GetUserNotesResponse> getAllNotes(String auth) {

        return notesFeignClient.getAllNotes(auth);
    }

    public GetNoteByIdResponse getNoteById(String auth, Integer id) {

        return notesFeignClient.getNoteById(auth, id);
    }

    public UpdateNoteResponse updateNote(String auth, Integer id, String contentUpdate, Integer version) {
        UpdateNoteRequest updateNoteRequest = UpdateNoteRequest.builder().content(contentUpdate).version(version).build();
        System.out.println(updateNoteRequest);
        return notesFeignClient.updateNote(auth, id, updateNoteRequest);
    }

    public DeleteNoteByIdResponse deleteNoteById(String auth, Integer id) {

        return notesFeignClient.deleteNoteById(auth, id);
    }
}
