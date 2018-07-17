package com.cedaniel200.practice.domain.user;

import com.cedaniel200.practice.model.User;
import com.cedaniel200.practice.repository.UserRepository;

import java.util.List;

public class UserDomainDefault implements UserDomain {

    private UserRepository repository;

    public UserDomainDefault(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<User> list() {
        return repository.list().getData();
    }
}
