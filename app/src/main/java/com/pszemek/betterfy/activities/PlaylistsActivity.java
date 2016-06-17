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

import java.io.IOException;
import java.util.List;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Ciemek on 12/06/16.
 */
public class PlaylistsActivity extends AppCompatActivity implements PlayerNotificationCallback{

    private final String    AUTHORIZATION = "Authorization";
    private final String    BEARER = "Bearer ";
    private String          spotifyAccessToken = null;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        spotifyAccessToken = getIntent().getStringExtra("spotifyAccessToken");
//        spotifyAccessToken = "BQCfylrqr5kQP8UML5Ur76s0mOzoWnBcimG54_kORpiNvXm_-6ynGru69w44JD9xPpu0U8bY9rvbslr153UWa0JnbnK5Zgbu7ytsGsXxBf7DH-QO-NYjIupe4LY-j4I_98bnDIjgURAOK6LxS9Au_HPyRwCabIBD50H5hYLv0a691BtueJmHWAD-2GA";


        Interceptor interceptorOAuth = new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                if (spotifyAccessToken != null) {
                    Request request = chain.request();

                    request = request.newBuilder()
                            .addHeader(AUTHORIZATION, BEARER + spotifyAccessToken)
                            .build();

                    return chain.proceed(request);
                }

                return null;
            }
        };

        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.interceptors().add(interceptorOAuth);
        OkHttpClient client = builder.build();


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(SpotifyAuthorizationScopes.BASE_URL_API)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();



        PlaylistService playlistApi = retrofit.create(PlaylistService.class);

        Call<PlaylistsModel> call = playlistApi.getLoggedUserPlaylists(10, 0);

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
