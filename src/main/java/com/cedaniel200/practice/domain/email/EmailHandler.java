package com.cedaniel200.practice.domain.email;

import com.cedaniel200.practice.model.Email;

public interface EmailHandler {

    boolean send(Email email);

    int getAmountOfEmailsSent();

    int getAmountOfEmailsNotSend();

}
