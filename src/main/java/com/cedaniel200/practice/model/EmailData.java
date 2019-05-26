package com.cedaniel200.practice.model;

public class EmailData implements Email {

    private String to;
    private String subject;
    private String message;

    public EmailData(String to, String subject, String message) {
        this.to = to;
        this.subject = subject;
        this.message = message;
    }

    @Override
    public String getTo() {
        return to;
    }

    @Override
    public String getSubject() {
        return subject;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
