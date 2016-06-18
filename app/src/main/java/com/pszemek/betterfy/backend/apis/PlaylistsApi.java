package com.pszemek.betterfy.backend.apis;

import com.pszemek.betterfy.backend.interceptors.OAuthTokenInterceptor;
import com.pszemek.betterfy.backend.interceptors.PrintResponseInterceptor;
import com.pszemek.betterfy.backend.models.PlaylistsModel;
import com.pszemek.betterfy.backend.services.PlaylistsService;
import com.pszemek.betterfy.misc.SpotifyAuthorizationScopes;

import okhttp3.OkHttpClient;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Ciemek on 18/06/16.
 */
public class PlaylistsApi {

    //todo figure out nice architecture for apis

    private PlaylistsService playlistsService;


    public PlaylistsApi(String spotifyAccessToken) {

        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.interceptors().add(new OAuthTokenInterceptor(spotifyAccessToken));
        builder.interceptors().add(new PrintResponseInterceptor());

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(SpotifyAuthorizationScopes.BASE_URL_API)
                .addConverterFactory(GsonConverterFactory.create())
                .client(builder.build())
                .build();

        playlistsService = retrofit.create(PlaylistsService.class);
    }

    public void getLoggedUserPlaylists(int limit, int offset, Callback<PlaylistsModel> callback) {
        //todo write custom exception for improper offset (<0) or limit

        if (limit > 50 || limit < 1)
            return;


        playlistsService.getLoggedUserPlaylists(limit, offset).enqueue(
                callback
        );
    }

    public void getLoggedUserPlaylists() {

    }
}
