package com.cedaniel200.practice.service;

import com.cedaniel200.practice.model.User;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

import java.util.List;

public interface UserService {

    @GET("/users")
    Call<List<User>> listUsers();

    @GET("/users/{id}")
    Call<User> findById(@Path("id") int id);

}
