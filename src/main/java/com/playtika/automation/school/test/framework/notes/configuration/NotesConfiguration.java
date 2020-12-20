package com.playtika.automation.school.test.framework.notes.configuration;

import com.playtika.automation.school.test.framework.notes.action.NotesActions;
import com.playtika.automation.school.test.framework.notes.client.NotesFeignClient;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@EnableFeignClients(clients = NotesFeignClient.class)
@Configuration
@EnableAutoConfiguration
public class NotesConfiguration {

    @Bean
    public NotesActions notesActions(NotesFeignClient client) {
        return new NotesActions(client);
    }
}