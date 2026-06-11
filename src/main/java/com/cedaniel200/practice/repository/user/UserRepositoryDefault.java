package com.cedaniel200.practice.repository.user;

import com.cedaniel200.practice.exception.ServiceNotAvailableException;
import com.cedaniel200.practice.model.User;
import com.cedaniel200.practice.service.UserService;
import retrofit2.Call;
import retrofit2.Retrofit;

import java.io.IOException;
import java.util.List;

public class UserRepositoryDefault implements UserRepository {

    private UserService service;

    public UserRepositoryDefault(Retrofit retrofit) {
        service = retrofit.create(UserService.class);
    }

    @Override
    public List<User> list() throws ServiceNotAvailableException {
        try {
            return tryGetList();
        } catch (IOException e) {
            throw new ServiceNotAvailableException("User Service Not Available", e);
        }
    }

    private List<User> tryGetList() throws IOException {
        Call<List<User>> usersCall = service.listUsers();
        return usersCall.execute().body();
    }

    @Override
    public User findById(int id) throws ServiceNotAvailableException {
        try {
            return tryFindById(id);
        } catch (IOException e) {
            throw new ServiceNotAvailableException("User Service Not Available", e);
        }
    }

    private User tryFindById(int id) throws IOException {
        Call<User> userCall = service.findById(id);
        return userCall.execute().body();
    }

}
