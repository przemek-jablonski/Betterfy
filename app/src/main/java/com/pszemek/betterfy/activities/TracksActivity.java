package com.pszemek.betterfy.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.pszemek.betterfy.R;
import com.pszemek.betterfy.adapters.RecyclerOnPosClickListener;
import com.pszemek.betterfy.adapters.TracksAdapter;
import com.pszemek.betterfy.backend.apis.TracksApi;
import com.pszemek.betterfy.backend.models.PlaylistTrackObject;
import com.pszemek.betterfy.backend.models.SpotifyBaseResponse;
import com.pszemek.betterfy.decorations.HorizontalSeparatorsDecoration;
import com.pszemek.betterfy.misc.Utils;
import com.spotify.sdk.android.authentication.AuthenticationResponse;
import com.spotify.sdk.android.player.Config;
import com.spotify.sdk.android.player.Player;
import com.spotify.sdk.android.player.PlayerNotificationCallback;
import com.spotify.sdk.android.player.PlayerState;
import com.spotify.sdk.android.player.Spotify;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Ciemek on 20/06/16.
 */
public class TracksActivity extends AppCompatActivity implements PlayerNotificationCallback {

    @BindView(R.id.tracks_recycler)
    RecyclerView tracksRecycler;

    private RecyclerView.LayoutManager layoutManager;
    private TracksAdapter   adapter;
    private TracksApi       tracksApi;

    private Intent          playActivityLaunchIntent;

    private Player          spotifyPlayer;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tracks);
        ButterKnife.bind(this);

        tracksApi = new TracksApi(
                true, Utils.getSpotifyAccessToken(this),
                true
        );

        buildRecycler();
        initializePlayer();

        tracksApi.getPlaylistTracks(
                Utils.getStringFromSharedPreferences(this, R.string.sharedpreferences_global, "userId"),
                getIntent().getStringExtra("playlistId"),
                null,
                null,
                null,
                new Callback<SpotifyBaseResponse<PlaylistTrackObject>>() {
                    @Override
                    public void onResponse(Call<SpotifyBaseResponse<PlaylistTrackObject>> call, Response<SpotifyBaseResponse<PlaylistTrackObject>> response) {
                        adapter.updateItems(response.body());
//                        Snackbar.make(getWindow().getDecorView().getRootView(), "OK: got " + fetchedTracks + " tracks", Snackbar.LENGTH_LONG).show();
                    }

                    @Override
                    public void onFailure(Call<SpotifyBaseResponse<PlaylistTrackObject>> call, Throwable t) {
//                        Snackbar.make(getWindow().getDecorView().getRootView(), "NOK: playlist fetching failed", Snackbar.LENGTH_LONG).show();
                    }
                }
        );

    }

    private void buildRecycler() {
        layoutManager = new LinearLayoutManager(this);
        adapter = new TracksAdapter(new RecyclerOnPosClickListener() {
            @Override
            public void onPosClick(View v, int position) {
                PlaylistTrackObject clickedTrack = adapter.getItem(position);
                playTrack(clickedTrack.track.uri);
                Snackbar.make(
                        getWindow().getDecorView().getRootView(),
                        "NOW PLAYING: " + clickedTrack.track.name,
                        Snackbar.LENGTH_LONG
                ).show();
            }
        });

        tracksRecycler.setLayoutManager(layoutManager);
        tracksRecycler.setHasFixedSize(true);
        tracksRecycler.addItemDecoration(new HorizontalSeparatorsDecoration(this));
        tracksRecycler.setAdapter(adapter);
    }

    private void initializePlayer() {
        SharedPreferences prefsAppData = getSharedPreferences(getString(R.string.sharedpreferences_global), Context.MODE_PRIVATE);
        if (prefsAppData.getInt(getString(R.string.responseType), -1) == AuthenticationResponse.Type.TOKEN.ordinal()) {

            Config config = new Config(
                    this,
                    prefsAppData.getString(getString(R.string.spotifyAccessToken_value), null),
                    prefsAppData.getString(getString(R.string.clientId), null)
            );
            spotifyPlayer = Spotify.getPlayer(config, this, new Player.InitializationObserver() {
                @Override
                public void onInitialized(Player player) {
                    player.addConnectionStateCallback(MainActivity.connectionStateCallback);
                    player.addPlayerNotificationCallback(TracksActivity.this);
                }

                @Override
                public void onError(Throwable throwable) {
                    Log.e("PLAYER", "ERROR: " + throwable.getMessage());
                }
            });

        }
    }

    private void playTrack(final String spotifyTrackUri) {
        spotifyPlayer.pause();
        spotifyPlayer.play(spotifyTrackUri);
    }



    @Override
    protected void onDestroy() {
        Spotify.destroyPlayer(this);
        super.onDestroy();
    }

    @Override
    public void onPlaybackEvent(EventType eventType, PlayerState playerState) {

    }

    @Override
    public void onPlaybackError(ErrorType errorType, String s) {

    }
}
