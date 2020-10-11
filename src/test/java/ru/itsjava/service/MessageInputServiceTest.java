package ru.itsjava.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import service.MessageInputService;

import java.io.ByteArrayInputStream;

@DisplayName("Класс MessageInputService: ")
public class MessageInputServiceTest {

    @DisplayName(" должен корректно читать сообщения из потока")
    @Test
    public void shouldHaveCorrectReadMessage(){
        MessageInputService messageInputService = new MessageInputService
                (new ByteArrayInputStream(new byte[]{12, 44, 22}));

        Assertions.assertEquals((char) 12, messageInputService.readMessage().charAt(0));
    }
}
