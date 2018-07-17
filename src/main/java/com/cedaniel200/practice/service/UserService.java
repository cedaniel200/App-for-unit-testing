package com.cedaniel200.practice.service;

import com.cedaniel200.practice.model.UsersSummary;
import retrofit2.Call;
import retrofit2.http.GET;

public interface UserService {

    @GET("/api/users")
    Call<UsersSummary> listUsers();

}
