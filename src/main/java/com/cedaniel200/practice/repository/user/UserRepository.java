package com.cedaniel200.practice.repository.user;

import com.cedaniel200.practice.exception.ServiceNotAvailableException;
import com.cedaniel200.practice.model.User;

import java.util.List;

public interface UserRepository {

    List<User> list() throws ServiceNotAvailableException;

    User findById(int id) throws ServiceNotAvailableException;

}
