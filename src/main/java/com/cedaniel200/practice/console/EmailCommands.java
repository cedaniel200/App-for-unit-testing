package com.cedaniel200.practice.console;

import com.cedaniel200.practice.domain.email.EmailDomain;
import com.cedaniel200.practice.model.EmailData;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
public class EmailCommands {

    public static final String DEFAULT_MESSAGE = "We try to send your email, we hope you run with luck";
    private EmailDomain emailDomain;

    public EmailCommands(EmailDomain emailDomain) {
        this.emailDomain = emailDomain;
    }

    @ShellMethod(value = "Send email")
    public String send(String to, String subject, String message) {
        emailDomain.sendMail(new EmailData(to, subject, message));
        return DEFAULT_MESSAGE;
    }
}
