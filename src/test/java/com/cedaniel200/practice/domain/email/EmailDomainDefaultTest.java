package com.cedaniel200.practice.domain.email;

import com.cedaniel200.practice.model.Email;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EmailDomainDefaultTest {

    private EmailDomain emailDomain;
    private EmailHandleStubSpy emailHandleStub;

    @BeforeEach
    void setup(){
        emailHandleStub = new EmailHandleStubSpy();
        emailDomain = new EmailDomainDefault(emailHandleStub);
    }

    @Test
    void sendValid_email_shouldIncrementEmailsSentCounter(){
        Email email = new EmailDummy();
        emailDomain.sendMail(email);
        assertEquals(1, emailHandleStub.getAmountOfEmailsSent());
        assertEquals(0, emailHandleStub.getAmountOfEmailsNotSend());
    }

    @Test
    void sendEmail_whenFails_shouldIncrementEmailsNotSentCounter(){
        emailDomain.sendMail(null);
        assertEquals(0, emailHandleStub.getAmountOfEmailsSent());
        assertEquals(1, emailHandleStub.getAmountOfEmailsNotSend());
    }

}

class EmailHandleStubSpy implements EmailHandler {

    private int emailsSend = 0;
    private int emailsNotSend = 0;

    @Override
    public boolean send(Email email) {
        if (email != null) {
            emailsSend++;
            return true;
        } else {
            emailsNotSend++;
            return false;
        }
    }

    @Override
    public int getAmountOfEmailsSent() {
        return emailsSend;
    }

    @Override
    public int getAmountOfEmailsNotSend() {
        return emailsNotSend;
    }
}

class EmailDummy implements Email {

    @Override
    public String getTo() {
        return null;
    }

    @Override
    public String getSubject() {
        return null;
    }

    @Override
    public String getMessage() {
        return null;
    }
}
