package com.cedaniel200.practice.model;

import java.util.Calendar;

public class Phrase {

    private long id;
    private final String phrase;
    private final String author;
    private final Calendar creationDate;

    public Phrase(String phrase, String author, Calendar creationDate) {
        this.phrase = phrase;
        this.author = author;
        this.creationDate = creationDate;
    }

    public Phrase(String phrase, String author) {
        this.phrase = phrase;
        this.author = author;
        this.creationDate = Calendar.getInstance();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPhrase() {
        return phrase;
    }

    public String getAuthor() {
        return author;
    }

    public Calendar getCreationDate() {
        return creationDate;
    }
}
