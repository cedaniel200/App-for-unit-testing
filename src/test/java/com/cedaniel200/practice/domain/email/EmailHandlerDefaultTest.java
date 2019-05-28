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
    public void  mustIsSuccessfulIfEmailIsSend(){
        Email email = new EmailData("email@email.com", "hi", "Hello");
        emailHandler.send(email);
        Assert.assertEquals(1, emailHandler.getAmountOfEmailsSent());
        Assert.assertTrue(((JavaMailSenderStub)javaMailSender).isCalled());
    }

    @Test
    public void  mustIsSuccessfulIfEmailIsNotSend(){
        emailHandler.send(null);
        Assert.assertEquals(1, emailHandler.getAmountOfEmailsNotSend());
        Assert.assertFalse(((JavaMailSenderStub)javaMailSender).isCalled());
    }
}

class JavaMailSenderStub implements JavaMailSender {

    private boolean isCalled;

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
        isCalled = true;
    }

    @Override
    public void send(SimpleMailMessage... simpleMessages) throws MailException {

    }

    public boolean isCalled() {
        return isCalled;
    }
}
