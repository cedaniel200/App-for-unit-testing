package com.cedaniel200.practice.domain.email;

import com.cedaniel200.practice.model.Email;

public interface EmailDomain {
    boolean sendMail(Email email);
}
