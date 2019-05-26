package com.cedaniel200.practice.domain.email;

import com.cedaniel200.practice.model.Email;

public class EmailDomainDefault implements EmailDomain {

    private EmailHandler emailHandler;

    public EmailDomainDefault(EmailHandler emailHandler) {
        this.emailHandler = emailHandler;
    }

    @Override
    public void sendMail(Email email) {
        emailHandler.send(email);
    }
}
