package com.pszemek.betterfy.backend.apis;

import android.support.annotation.NonNull;

import com.pszemek.betterfy.backend.models.UserModel;
import com.pszemek.betterfy.backend.services.UserService;

import retrofit2.Callback;

/**
 * Created by Ciemek on 20/06/16.
 */
public class UserApi extends BaseApi {

    private UserService service;

    public UserApi(boolean attachOAuthTokenInterceptor, String spotifyAuthorizationToken, boolean attachPrintResponseInterceptor) {
        super(attachOAuthTokenInterceptor, spotifyAuthorizationToken, attachPrintResponseInterceptor);

        service = retrofit.create(UserService.class);
    }

    public void getLoggedUser(final Callback<UserModel> callback) {
        service.getLoggedUser().enqueue(callback);
    }

    public void getUser(@NonNull String userId, final Callback<UserModel> callback) {
        service.getUser(userId).enqueue(callback);
    }
}
