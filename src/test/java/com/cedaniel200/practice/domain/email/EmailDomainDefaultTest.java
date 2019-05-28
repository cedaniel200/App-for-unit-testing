package com.cedaniel200.practice.domain.email;

import com.cedaniel200.practice.model.Email;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class EmailDomainDefaultTest {

    private EmailDomain emailDomain;
    private EmailHandleStub emailHandleStub;

    @Before
    public void setup(){
        emailHandleStub = new EmailHandleStub();
        emailDomain = new EmailDomainDefault(emailHandleStub);
    }

    @Test
    public void mustIsSuccessfulIfEmailIsSend(){
        Email email = new EmailDummy();
        emailDomain.sendMail(email);
        Assert.assertEquals(1, emailHandleStub.getAmountOfEmailsSent());
        Assert.assertEquals(0, emailHandleStub.getAmountOfEmailsNotSend());
    }
    @Test
    public void mustIsSuccessfulIfEmailIsNotSend(){
        emailDomain.sendMail(null);
        Assert.assertEquals(0, emailHandleStub.getAmountOfEmailsSent());
        Assert.assertEquals(1, emailHandleStub.getAmountOfEmailsNotSend());
    }

}

class EmailHandleStub implements EmailHandler {

    private int emailsSend = 0;
    private int emailsNotSend = 0;

    @Override
    public void send(Email email) {
        if (email != null) {
            emailsSend++;
        } else {
            emailsNotSend++;
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
