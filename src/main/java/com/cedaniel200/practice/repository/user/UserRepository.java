package com.cedaniel200.practice.repository.user;

import com.cedaniel200.practice.exception.ServiceNotAvailableException;
import com.cedaniel200.practice.model.User;
import com.cedaniel200.practice.model.UsersSummary;

public interface UserRepository {

    UsersSummary list() throws ServiceNotAvailableException;

    User findById(int id) throws ServiceNotAvailableException;

}
