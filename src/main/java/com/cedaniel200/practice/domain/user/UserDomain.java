package com.cedaniel200.practice.domain.user;

import com.cedaniel200.practice.exception.MalformedDataException;
import com.cedaniel200.practice.exception.ServiceNotAvailableException;
import com.cedaniel200.practice.model.User;

import java.util.List;

public interface UserDomain {

    List<User> list() throws ServiceNotAvailableException, MalformedDataException;

    User findById(int id) throws ServiceNotAvailableException, MalformedDataException;

}
