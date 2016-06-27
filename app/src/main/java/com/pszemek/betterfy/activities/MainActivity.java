package com.pszemek.betterfy.activities;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import com.pszemek.betterfy.R;
import com.pszemek.betterfy.backend.apis.UserApi;
import com.pszemek.betterfy.backend.models.UserPrivateObject;
import com.pszemek.betterfy.misc.SpotifyAuthorizationScopes;
import com.pszemek.betterfy.misc.Utils;
import com.spotify.sdk.android.authentication.AuthenticationClient;
import com.spotify.sdk.android.authentication.AuthenticationRequest;
import com.spotify.sdk.android.authentication.AuthenticationResponse;
import com.spotify.sdk.android.player.Config;
import com.spotify.sdk.android.player.ConnectionStateCallback;
import com.spotify.sdk.android.player.PlayerNotificationCallback;
import com.spotify.sdk.android.player.PlayerState;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements PlayerNotificationCallback, ConnectionStateCallback {


    private static final String CLIENT_ID = "3cc8f8cb00db48f18d3329e09f806975";
    private static final String REDIRECT_URI = "betterfy://callback";
    private static final int    REQUEST_CODE = 0;

    private UserApi             userApi;


    @BindView(R.id.button_show_all_playlists)
    Button showAllPlaylistsButton;

    @OnClick(R.id.button_show_all_playlists)
    public void showAllPlaylistsClick() {
        Intent playlistActivityIntent = new Intent(getApplicationContext(), PlaylistsActivity.class);
        startActivity(playlistActivityIntent);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        AuthenticationRequest.Builder builder =
                new AuthenticationRequest.Builder(
                        CLIENT_ID,
                        AuthenticationResponse.Type.TOKEN,
                        REDIRECT_URI);

        builder.setScopes(SpotifyAuthorizationScopes.FULL_ACCESS_SCOPES);
        AuthenticationRequest request = builder.build();

        Log.e("STARTUP", "SPOTIFY TOKEN: " + Utils.getStringFromSharedPreferences(this, R.string.sharedpreferences_userdata, getString(R.string.spotifyAccessToken_value)));
        Log.e("STARTUP", "SPOTIFY EXPIRY: " + Utils.getSharedPreferences(this, R.string.sharedpreferences_userdata).getInt(getString(R.string.spotifyAccessToken_expiration), -1));

        Snackbar.make(
                getWindow().getDecorView().getRootView(),
                Utils.checkInternetConnectionString(this),
                Snackbar.LENGTH_LONG
        ).show();

        AuthenticationClient.openLoginActivity(this, REQUEST_CODE, request);
    }

    @SuppressLint("CommitPrefEdits")
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);

        // Check if result comes from the correct activity
        if (requestCode == REQUEST_CODE) {

            AuthenticationResponse response = AuthenticationClient.getResponse(resultCode, intent);

            SharedPreferences prefs = getSharedPreferences(getString(R.string.sharedpreferences_userdata), Context.MODE_PRIVATE);

            //todo: if accesstoken is already put in prefs and its expiry date is valid, then dont launch AuthenticationClient.openLogin(...)

            prefs.edit()
                    .putString(
                            getString(R.string.spotifyAccessToken_value),
                            response.getAccessToken()
                    )
                    .putInt(
                            getString(R.string.spotifyAccessToken_expiration),
                            response.getExpiresIn()
                    )
                    .commit();

            //fetching current user data
            //todo: do it in background thread, this info is not critically nessesary from the beginning
            userApi = new UserApi(true, prefs.getString(getString(R.string.spotifyAccessToken_value), null), true);
            userApi.getLoggedUser(new Callback<UserPrivateObject>() {
                @Override
                public void onResponse(Call<UserPrivateObject> call, Response<UserPrivateObject> response) {
                    Log.e("Retrofit", "getLoggedUser: onresponse");
                    UserPrivateObject user = response.body();
                    getSharedPreferences(getString(R.string.sharedpreferences_userdata), Context.MODE_PRIVATE).edit()
                            .putString("country", user.country)
                            .putString("display_name", user.displayName)
                            .putInt("followers_count", user.followers.totalFollowers)
                            .putString("userId", user.id)
                            .putString("image_href", user.images.get(0).url)
                            .putString("product", user.product)
                            .putString("type", user.type)
                            .putString("spotify_uri", user.uri)
                            .apply();
                }

                @Override
                public void onFailure(Call<UserPrivateObject> call, Throwable t) {
                    Log.e("Retrofit", "getLoggedUser: onfailure");
                    //todo exception
                }
            });



            //launching player
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
//                });           play
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
