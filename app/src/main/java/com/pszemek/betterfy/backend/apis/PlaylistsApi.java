package com.pszemek.betterfy.backend.apis;

import com.pszemek.betterfy.backend.models.SpotifyBaseResponse;
import com.pszemek.betterfy.backend.models.PlaylistObject;
import com.pszemek.betterfy.backend.services.PlaylistsService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Ciemek on 18/06/16.
 */
public class PlaylistsApi extends BaseApi {

    private PlaylistsService service;

    public PlaylistsApi(boolean attachOAuthTokenInterceptor, String spotifyAuthorizationToken, boolean attachPrintResponseInterceptor) {
        super(attachOAuthTokenInterceptor, spotifyAuthorizationToken, attachPrintResponseInterceptor);

        createService();
    }

    public PlaylistsService createService() {
        service = retrofit.create(PlaylistsService.class);
        return service;
    }

    public void getLoggedUserPlaylists(int limit, int offset, Callback<SpotifyBaseResponse<PlaylistObject>> callback) {

        if (limit > 50 || limit < 1 || offset < 0) {
            //todo write custom exception for improper OFFSET or LIMIT
            return;
        }

        service.getLoggedUserPlaylists(limit, offset).enqueue(callback);
    }

    //TODO MAKE ALL PLAYLISTS VISIBLE
    public void getLoggedUserAllPlaylists(final Callback<SpotifyBaseResponse<PlaylistObject>> callback) {

        service.getLoggedUserPlaylists(50, 0).enqueue(new Callback<SpotifyBaseResponse<PlaylistObject>>() {
            @Override
            public void onResponse(Call<SpotifyBaseResponse<PlaylistObject>> call, Response<SpotifyBaseResponse<PlaylistObject>> response) {
                callback.onResponse(call, response);
            }

            @Override
            public void onFailure(Call<SpotifyBaseResponse<PlaylistObject>> call, Throwable t) {
                callback.onFailure(call, t);
            }
        });
    }

    public PlaylistsService getService() {
        return service;
    }
}
