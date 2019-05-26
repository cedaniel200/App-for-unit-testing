package com.cedaniel200.practice.service;

import com.cedaniel200.practice.model.User;
import com.cedaniel200.practice.model.UsersSummary;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

import java.util.HashMap;

public interface UserService {

    @GET("/api/users")
    Call<UsersSummary> listUsers();

    @GET("/api/users/{id}")
    Call<HashMap<String, User>> findById(@Path("id") int id);

}
