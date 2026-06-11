package com.cedaniel200.practice.domain.email;

import com.cedaniel200.practice.model.Email;

public class EmailDomainDefault implements EmailDomain {

    private EmailHandler emailHandler;

    public EmailDomainDefault(EmailHandler emailHandler) {
        this.emailHandler = emailHandler;
    }

    @Override
    public boolean sendMail(Email email) {
        return emailHandler.send(email);
    }
}
