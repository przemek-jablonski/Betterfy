package com.pszemek.betterfy.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.pszemek.betterfy.R;
import com.pszemek.betterfy.misc.SpotifyAuthorizationScopes;
import com.spotify.sdk.android.authentication.AuthenticationClient;
import com.spotify.sdk.android.authentication.AuthenticationHandler;
import com.spotify.sdk.android.authentication.AuthenticationRequest;
import com.spotify.sdk.android.authentication.AuthenticationResponse;
import com.spotify.sdk.android.player.Config;
import com.spotify.sdk.android.player.ConnectionStateCallback;
import com.spotify.sdk.android.player.Player;
import com.spotify.sdk.android.player.PlayerNotificationCallback;
import com.spotify.sdk.android.player.PlayerState;
import com.spotify.sdk.android.player.Spotify;

public class MainActivity extends AppCompatActivity implements PlayerNotificationCallback, ConnectionStateCallback {

    // TODO: Replace with your client ID
    private static final String CLIENT_ID = "3cc8f8cb00db48f18d3329e09f806975";
    // TODO: Replace with your redirect URI
    private static final String REDIRECT_URI = "betterfy://callback";

    // Request code that will be passed together with authentication result to the onAuthenticationResult callback
    // Can be any integer
    private static final int REQUEST_CODE = 0;

    private String spotifyAccessToken = null;

//    private Player mPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AuthenticationRequest.Builder builder =
                new AuthenticationRequest.Builder(CLIENT_ID, AuthenticationResponse.Type.TOKEN, REDIRECT_URI);
//        builder.setScopes(new String[]{"user-read-private", "streaming"});
        builder.setScopes(SpotifyAuthorizationScopes.FULL_ACCESS_SCOPES);
        AuthenticationRequest request = builder.build();

        AuthenticationClient.openLoginActivity(this, REQUEST_CODE, request);
        findViewById(R.id.button_show_all_playlists).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent playlistActivityIntent = new Intent(getApplicationContext(), PlaylistsActivity.class);
                playlistActivityIntent.putExtra("spotifyAccessToken", spotifyAccessToken);
                startActivity(playlistActivityIntent);
                playlistActivityIntent = null;
            }
        });
//        Intent intent = new Intent(this, LoginActivity.class);
//        intent.putExtra("EXTRA_AUTH_REQUEST", request);
//        startActivityForResult(intent, REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);

        // Check if result comes from the correct activity
        if (requestCode == REQUEST_CODE) {
            AuthenticationResponse response = AuthenticationClient.getResponse(resultCode, intent);
            spotifyAccessToken = response.getAccessToken();
            if (response.getType() == AuthenticationResponse.Type.TOKEN) {
                Config playerConfig = new Config(this, response.getAccessToken(), CLIENT_ID);
//                mPlayer = Spotify.getPlayer(playerConfig, this, new Player.InitializationObserver() {
//                    @Override
//                    public void onInitialized(Player player) {
//                        mPlayer.addConnectionStateCallback(MainActivity.this);
//                        mPlayer.addPlayerNotificationCallback(MainActivity.this);
//                        mPlayer.play("spotify:track:2TpxZ7JUBn3uw46aR7qd6V");
//                    }

//                    @Override
//                    public void onError(Throwable throwable) {
//                        Log.e("MainActivity", "Could not initialize player: " + throwable.getMessage());
//                    }
//                });
            }
        }
    }

    @Override
    public void onLoggedIn() {
        Log.d("MainActivity", "User logged in");
    }

    @Override
    public void onLoggedOut() {
        Log.d("MainActivity", "User logged out");
    }

    @Override
    public void onLoginFailed(Throwable error) {
        Log.d("MainActivity", "Login failed");
    }

    @Override
    public void onTemporaryError() {
        Log.d("MainActivity", "Temporary error occurred");
    }

    @Override
    public void onConnectionMessage(String message) {
        Log.d("MainActivity", "Received connection message: " + message);
    }

    @Override
    public void onPlaybackEvent(PlayerNotificationCallback.EventType eventType, PlayerState playerState) {
        Log.d("MainActivity", "Playback event received: " + eventType.name());
        switch (eventType) {
            // Handle event type as necessary
            default:
                break;
        }
    }

    @Override
    public void onPlaybackError(PlayerNotificationCallback.ErrorType errorType, String errorDetails) {
        Log.d("MainActivity", "Playback error received: " + errorType.name());
        switch (errorType) {
            // Handle error type as necessary
            default:
                break;
        }
    }

    @Override
    protected void onDestroy() {
        // VERY IMPORTANT! This must always be called or else you will leak resources
//        Spotify.destroyPlayer(this);
        super.onDestroy();
    }
}
