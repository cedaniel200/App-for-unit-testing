package com.cedaniel200.practice.domain.email;

import com.cedaniel200.practice.model.Email;
import com.cedaniel200.practice.model.EmailData;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessagePreparator;

import javax.mail.internet.MimeMessage;
import java.io.InputStream;

public class EmailHandlerDefaultTest {

    private EmailHandler emailHandler;
    private JavaMailSender javaMailSender;

    @Before
    public void setup(){
        javaMailSender = new JavaMailSenderStub();
        emailHandler = new EmailHandlerDefault(javaMailSender);
    }

    @Test
    public void mustBeSuccessfulIfEmailIsSend(){
        Email email = new EmailData("email@email.com", "hi", "Hello");

        emailHandler.send(email);

        Assert.assertEquals(1, emailHandler.getAmountOfEmailsSent());
    }

    @Test
    public void mustBeSuccessfulIfEmailIsNotSend(){
        emailHandler.send(null);

        Assert.assertEquals(1, emailHandler.getAmountOfEmailsNotSend());
    }
}

class JavaMailSenderStub implements JavaMailSender {

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
        // No es necesario hacer nada, no se quiere enviar el email
    }

    @Override
    public void send(SimpleMailMessage... simpleMessages) throws MailException {

    }

}
