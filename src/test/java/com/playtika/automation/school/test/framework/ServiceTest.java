package com.playtika.automation.school.test.framework;

import java.util.List;
import java.util.Optional;

import feign.FeignException;
import org.apache.commons.lang.RandomStringUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import com.playtika.automation.school.test.framework.login.action.AuthActions;
import com.playtika.automation.school.test.framework.login.pojo.responses.AuthenticateResponse;
import com.playtika.automation.school.test.framework.notes.action.NotesActions;
import com.playtika.automation.school.test.framework.login.configuration.AuthConfiguration;
import com.playtika.automation.school.test.framework.notes.configuration.NotesConfiguration;
import com.playtika.automation.school.test.framework.notes.pojo.responses.CreateNoteResponse;
import com.playtika.automation.school.test.framework.notes.pojo.responses.DeleteNoteByIdResponse;
import com.playtika.automation.school.test.framework.notes.pojo.responses.GetNoteByIdResponse;
import com.playtika.automation.school.test.framework.notes.pojo.responses.GetUserNotesResponse;
import com.playtika.automation.school.test.framework.notes.pojo.responses.UpdateNoteResponse;
import com.playtika.automation.school.test.framework.registration.action.RegisterActions;
import com.playtika.automation.school.test.framework.registration.configuration.RegisterConfiguration;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;


@SpringBootTest(classes = {AuthConfiguration.class, NotesConfiguration.class, RegisterConfiguration.class})


public class ServiceTest {

    @Autowired
    private AuthActions authActions;
    @Autowired
    private RegisterActions registerActions;
    @Autowired
    private NotesActions notesActions;


    private AuthenticateResponse authenticateResponse;

    @BeforeEach
    void setUp() {
        String email = generateRandomEmail();
        String password = generateRandomPassword();
        registerActions.registerUser(email, password);

        authenticateResponse = authActions.authorization(email, password);
    }

    @Test
    void registerUser() {
        String auth = "Bearer " + authenticateResponse.getAccessToken();
        String firstContent = "First Note";
        CreateNoteResponse createNoteResponse = notesActions.createNote(auth, firstContent);
        Integer newNote = 0;
        assertThat(createNoteResponse.getId()).isNotNull();
        assertThat(createNoteResponse.getContent()).isEqualTo(firstContent);
        assertThat(createNoteResponse.getVersion()).isEqualTo(newNote);
        Integer firstNoteId = createNoteResponse.getId();
        Integer version = createNoteResponse.getVersion();

        List<GetUserNotesResponse> getUserNotesResponse = notesActions.getAllNotes(auth);
        assertThat(getUserNotesResponse.size()).isEqualTo(1);

        String secondContent = "Second Note";
        CreateNoteResponse createSecondNoteResponse = notesActions.createNote(auth, secondContent);
        Integer secondId = createSecondNoteResponse.getId();
        assertThat(createSecondNoteResponse.getId()).isNotNull();
        assertThat(createSecondNoteResponse.getContent()).isEqualTo(secondContent);
        assertThat(createSecondNoteResponse.getVersion()).isEqualTo(newNote);

        getUserNotesResponse = notesActions.getAllNotes(auth);
        assertThat(getUserNotesResponse.size()).isEqualTo(2);

        GetNoteByIdResponse getNoteByIdResponse = notesActions.getNoteById(auth, firstNoteId);
        assertThat(getNoteByIdResponse.getId()).isNotNull();
        assertThat(getNoteByIdResponse.getContent()).isEqualTo(firstContent);
        assertThat(getNoteByIdResponse.getVersion()).isEqualTo(newNote);

        String contentUpdate = "First Note - Updated";
        UpdateNoteResponse updateNoteResponse = notesActions.updateNote(auth, firstNoteId, contentUpdate, version);
        assertThat(updateNoteResponse.getId()).isEqualTo(firstNoteId);
        assertThat(updateNoteResponse.getContent()).isEqualTo(contentUpdate);
        assertThat(updateNoteResponse.getVersion()).isNotEqualTo(newNote);

        getUserNotesResponse = notesActions.getAllNotes(auth);
        Optional<GetUserNotesResponse> filterNote = getUserNotesResponse.stream().filter(note -> note.getId() == firstNoteId).findFirst();
        assertThat(filterNote.get().getId()).isEqualTo(firstNoteId);

        DeleteNoteByIdResponse deleteNoteById = notesActions.deleteNoteById(auth, firstNoteId);

        getUserNotesResponse = notesActions.getAllNotes(auth);
        assertThat(getUserNotesResponse.size()).isEqualTo(1);
        assertThat(getUserNotesResponse.get(0).getId()).isNotEqualTo(firstNoteId);

        Exception exception = assertThrows(FeignException.class, () -> { notesActions.getNoteById(auth, firstNoteId); });
        exception.getMessage();
        System.out.println(exception.getMessage());

        deleteNoteById = notesActions.deleteNoteById(auth, secondId);
    }

    private String generateRandomPassword() {
        int count = 10;
        return String.format("%s", RandomStringUtils.randomAlphanumeric(count));
    }

    private String generateRandomEmail() {
        String domain = "@gmail.com";
        return String.format("%s%s", RandomStringUtils.randomAlphanumeric(10), domain);
    }
}