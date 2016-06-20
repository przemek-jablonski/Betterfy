package com.pszemek.betterfy.backend.apis;

import com.pszemek.betterfy.backend.interceptors.OAuthTokenInterceptor;
import com.pszemek.betterfy.backend.interceptors.PrintResponseInterceptor;
import com.pszemek.betterfy.backend.models.PlaylistsModel;
import com.pszemek.betterfy.backend.services.PlaylistsService;
import com.pszemek.betterfy.misc.SpotifyAuthorizationScopes;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Ciemek on 18/06/16.
 */
public class PlaylistsApi extends BaseApi {

    private PlaylistsService service;

    public PlaylistsApi(boolean attachOAuthTokenInterceptor, String spotifyAuthorizationToken, boolean attachPrintResponseInterceptor) {
        super(attachOAuthTokenInterceptor, spotifyAuthorizationToken, attachPrintResponseInterceptor);

        service = retrofit.create(PlaylistsService.class);
    }

    public void getLoggedUserPlaylists(int limit, int offset, Callback<PlaylistsModel> callback) {

        if (limit > 50 || limit < 1 || offset < 0) {
            //todo write custom exception for improper OFFSET or LIMIT
            return;
        }

        service.getLoggedUserPlaylists(limit, offset).enqueue(callback);
    }

    //TODO MAKE ALL PLAYLISTS VISIBLE
    public void getLoggedUserAllPlaylists(final Callback<PlaylistsModel> callback) {

        final PlaylistsModel playlistsModel;
        service.getLoggedUserPlaylists(50, 0).enqueue(new Callback<PlaylistsModel>() {
            @Override
            public void onResponse(Call<PlaylistsModel> call, Response<PlaylistsModel> response) {
                callback.onResponse(call, response);
            }

            @Override
            public void onFailure(Call<PlaylistsModel> call, Throwable t) {
                callback.onFailure(call, t);
            }
        });
    }
}
