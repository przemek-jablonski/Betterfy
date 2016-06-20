package com.pszemek.betterfy.backend.services;

import com.pszemek.betterfy.backend.models.UserModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Ciemek on 20/06/16.
 */
public interface UserService {

    @GET("me")
    Call<UserModel> getLoggedUser(
    );

    @GET("users/{user_id}")
    Call<UserModel> getUser(
            @Path("user_id") String userId
    );

}
