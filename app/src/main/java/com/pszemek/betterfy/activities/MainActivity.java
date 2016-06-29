package com.pszemek.betterfy.activities;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.pszemek.betterfy.R;
import com.pszemek.betterfy.adapters.MainAdapter;
import com.pszemek.betterfy.adapters.RecyclerOnPosClickListener;
import com.pszemek.betterfy.backend.apis.UserApi;
import com.pszemek.betterfy.misc.MainActivityItem;
import com.pszemek.betterfy.misc.MainActivityItemEnum;
import com.pszemek.betterfy.backend.models.UserPrivateObject;
import com.pszemek.betterfy.misc.SpotifyAuthorizationScopes;
import com.pszemek.betterfy.misc.Utils;
import com.spotify.sdk.android.authentication.AuthenticationClient;
import com.spotify.sdk.android.authentication.AuthenticationRequest;
import com.spotify.sdk.android.authentication.AuthenticationResponse;
import com.spotify.sdk.android.player.ConnectionStateCallback;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements ConnectionStateCallback {


    private static final String CLIENT_ID = "3cc8f8cb00db48f18d3329e09f806975";
    private static final String REDIRECT_URI = "betterfy://callback";
    private static final int    REQUEST_CODE = new Random().nextInt(65535);

    private UserApi             userApi;

    MainAdapter                 adapter;

    //todo: fix this static here, propably it's performance killer
    public static ConnectionStateCallback connectionStateCallback;

    @BindView(R.id.mainmenu_recycler)
    RecyclerView recyclerMainMenu;


    //todo open source licenses button


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

        Log.e("STARTUP", "SPOTIFY TOKEN: " + Utils.getStringFromSharedPreferences(this, R.string.sharedpreferences_global, getString(R.string.spotifyAccessToken_value)));
        Log.e("STARTUP", "SPOTIFY EXPIRY: " + Utils.getSharedPreferences(this, R.string.sharedpreferences_global).getInt(getString(R.string.spotifyAccessToken_expiration), -1));

        Snackbar.make(
                getWindow().getDecorView().getRootView(),
                Utils.checkInternetConnectionString(this),
                Snackbar.LENGTH_LONG
        ).show();

        connectionStateCallback = this;
        AuthenticationClient.openLoginActivity(this, REQUEST_CODE, request);

        buildRecycler();
    }


    @SuppressLint("CommitPrefEdits")
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);

        // Check if result comes from the correct activity
        if (requestCode == REQUEST_CODE) {

            AuthenticationResponse response = AuthenticationClient.getResponse(resultCode, intent);

            SharedPreferences prefs = getSharedPreferences(getString(R.string.sharedpreferences_global), Context.MODE_PRIVATE);

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
                    .putString(
                            getString(R.string.clientId),
                            CLIENT_ID
                    )
                    .putString(
                            getString(R.string.redirectUri),
                            REDIRECT_URI
                    )
                    .putInt(
                            getString(R.string.requestCode),
                            REQUEST_CODE
                    )
                    .putInt(
                            getString(R.string.responseType),
                            response.getType().ordinal()
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
                    getSharedPreferences(getString(R.string.sharedpreferences_global), Context.MODE_PRIVATE).edit()
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

        }
    }

    private void fillViews() {
        List<MainActivityItem> gridButtonList = new ArrayList<>();
        gridButtonList.add(new MainActivityItem(MainActivityItemEnum.ARTISTS));
        gridButtonList.add(new MainActivityItem(MainActivityItemEnum.ALBUMS));
        gridButtonList.add(new MainActivityItem(MainActivityItemEnum.PLAYLISTS));
        gridButtonList.add(new MainActivityItem(MainActivityItemEnum.DISCOVER));
        gridButtonList.add(new MainActivityItem(MainActivityItemEnum.TOP));

        adapter.updateItems(gridButtonList);
    }

    private void buildRecycler() {
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 2);
        recyclerMainMenu.setLayoutManager(layoutManager);
        recyclerMainMenu.setHasFixedSize(true);

        adapter = new MainAdapter();
        adapter.setRecyclerOnPosClickListener(new RecyclerOnPosClickListener() {
            @Override
            public void onPosClick(View v, int position) {
                //todo: clicking on a button doesnt do anything, fix it

                MainActivityItemEnum type = adapter.getItem(position).type;
                if (type == MainActivityItemEnum.PLAYLISTS) {
                    startActivity(new Intent(getApplicationContext(), PlaylistsActivity.class));
                } else if (type == MainActivityItemEnum.ALBUMS) {
                    startActivity(new Intent(getApplicationContext(), AlbumsActivity.class));
                } else {

                }
            }
        });

        fillViews();
        recyclerMainMenu.setAdapter(adapter);

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
    protected void onDestroy() {
        super.onDestroy();
    }
}
