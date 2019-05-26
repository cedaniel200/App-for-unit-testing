package com.cedaniel200.practice.domain.email;

import com.cedaniel200.practice.model.Email;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

public class EmailHandlerDefault implements EmailHandler {

    private JavaMailSender mailSender;
    private int amountEmailsSent;
    private int amountEmailsNotSent;

    public EmailHandlerDefault(JavaMailSender javaMailSender) {
        this.mailSender = javaMailSender;
        this.amountEmailsSent = 0;
        this.amountEmailsNotSent = 0;
    }

    @Override
    public void send(Email email) {
        try {
            trySend(email);
            amountEmailsSent++;
        }catch (Exception e){
            amountEmailsNotSent++;
        }
    }

    private void trySend(Email email) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email.getTo());
        message.setSubject(email.getSubject());
        message.setText(email.getMessage());
        mailSender.send(message);
    }

    @Override
    public int getAmountOfEmailsSent() {
        return amountEmailsSent;
    }

    @Override
    public int getAmountOfEmailsNotSend() {
        return amountEmailsNotSent;
    }
}
