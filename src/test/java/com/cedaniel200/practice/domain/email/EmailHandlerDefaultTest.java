package com.cedaniel200.practice.domain.email;

import com.cedaniel200.practice.model.Email;
import com.cedaniel200.practice.model.EmailData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessagePreparator;

import jakarta.mail.internet.MimeMessage;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EmailHandlerDefaultTest {

    private EmailHandler emailHandler;
    private JavaMailSender javaMailSender;

    @BeforeEach
    void setup(){
        javaMailSender = new JavaMailSenderStub();
        emailHandler = new EmailHandlerDefault(javaMailSender);
    }

    @Test
    void mustBeSuccessfulIfEmailIsSend(){
        Email email = new EmailData("email@email.com", "hi", "Hello");
        emailHandler.send(email);
        assertEquals(1, emailHandler.getAmountOfEmailsSent());
    }

    @Test
    void mustBeSuccessfulIfEmailIsNotSend(){
        emailHandler.send(null);
        assertEquals(1, emailHandler.getAmountOfEmailsNotSend());
    }
}

class JavaMailSenderStub implements JavaMailSender {

    List<SimpleMailMessage> emails;

    JavaMailSenderStub() {
        emails = new ArrayList<>();
    }

    @Override
    public MimeMessage createMimeMessage() {
        return null;
    }

    @Override
    public MimeMessage createMimeMessage(InputStream contentStream) throws MailException {
        return null;
    }

    @Override
    public void send(MimeMessage mimeMessage) throws MailException {

    }

    @Override
    public void send(MimeMessage... mimeMessages) throws MailException {

    }

    @Override
    public void send(MimeMessagePreparator mimeMessagePreparator) throws MailException {

    }

    @Override
    public void send(MimeMessagePreparator... mimeMessagePreparators) throws MailException {

    }

    @Override
    public void send(SimpleMailMessage simpleMessage) throws MailException {
        emails.add(simpleMessage);
    }

    @Override
    public void send(SimpleMailMessage... simpleMessages) throws MailException {

    }

}
