package com.cedaniel200.practice.repository.user;

import com.cedaniel200.practice.exception.ServiceNotAvailableException;
import com.cedaniel200.practice.model.User;
import com.cedaniel200.practice.model.UsersSummary;
import com.cedaniel200.practice.service.UserService;
import retrofit2.Call;
import retrofit2.Retrofit;

import java.io.IOException;
import java.util.HashMap;

public class UserRepositoryDefault implements UserRepository {

    private UserService service;

    public UserRepositoryDefault(Retrofit retrofit) {
        service = retrofit.create(UserService.class);
    }

    @Override
    public UsersSummary list() throws ServiceNotAvailableException {
        try {
            return tryGetList();
        } catch (IOException e) {
            throw new ServiceNotAvailableException("User Service Not Available", e);
        }
    }

    private UsersSummary tryGetList() throws IOException {
        Call<UsersSummary> usersCall = service.listUsers();
        return usersCall.execute().body();
    }

    @Override
    public User findById(int id) throws ServiceNotAvailableException {
        try {
            return tryFindById(id);
        } catch (NullPointerException | IOException e) {
            throw new ServiceNotAvailableException("User Service Not Available", e);
        }
    }

    private User tryFindById(int id) throws IOException {
        Call<HashMap<String, User>> userCall = service.findById(id);
        HashMap<String, User> user = userCall.execute().body();
        return user.get("data");
    }

}
