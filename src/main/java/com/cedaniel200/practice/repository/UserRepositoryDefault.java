package com.cedaniel200.practice.repository;

import com.cedaniel200.practice.model.UsersSummary;
import com.cedaniel200.practice.service.UserService;
import retrofit2.Call;
import retrofit2.Retrofit;

import java.io.IOException;

public class UserRepositoryDefault implements UserRepository {

    private UserService service;

    public UserRepositoryDefault(Retrofit retrofit) {
        service = retrofit.create(UserService.class);
    }

    @Override
    public UsersSummary list() {
        UsersSummary usersSummary;
        try {
            usersSummary = tryGetList();
        } catch (IOException e) {
            usersSummary = new UsersSummary();
        }
        return usersSummary;
    }

    private UsersSummary tryGetList() throws IOException {
        Call<UsersSummary> usersCall = service.listUsers();
        return usersCall.execute().body();
    }

}
