package com.pszemek.betterfy.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.pszemek.betterfy.backend.models.PlaylistsModel;
import com.pszemek.betterfy.backend.services.PlaylistService;
import com.pszemek.betterfy.misc.SpotifyAuthorizationScopes;
import com.spotify.sdk.android.player.PlayerNotificationCallback;
import com.spotify.sdk.android.player.PlayerState;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Ciemek on 12/06/16.
 */
public class PlaylistsActivity extends AppCompatActivity implements PlayerNotificationCallback{


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(SpotifyAuthorizationScopes.BASE_URL_API)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        PlaylistService playlistService = retrofit.create(PlaylistService.class);
        Call<PlaylistsModel> call = playlistService.getLoggedUserPlaylists(10, 0);

        call.enqueue(new Callback<PlaylistsModel>() {
            @Override
            public void onResponse(Call<PlaylistsModel> call, Response<PlaylistsModel> response) {
                Log.e("Retrofit", "success");
                Log.e("Retrofit", "success: list size: " + response.body().getPlaylists().size());
            }

            @Override
            public void onFailure(Call<PlaylistsModel> call, Throwable t) {
                Log.e("Retrofit", t.getMessage());
            }
        });


    }

    @Override
    public void onPlaybackEvent(EventType eventType, PlayerState playerState) {

    }

    @Override
    public void onPlaybackError(ErrorType errorType, String s) {

    }
}
