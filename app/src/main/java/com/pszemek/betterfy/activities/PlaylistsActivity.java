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
import com.pszemek.betterfy.adapters.PlaylistsAdapter;
import com.pszemek.betterfy.adapters.RecyclerOnPosClickListener;
import com.pszemek.betterfy.backend.apis.PlaylistsApi;
import com.pszemek.betterfy.backend.models.SpotifyBaseResponse;
import com.pszemek.betterfy.backend.models.PlaylistObject;
import com.pszemek.betterfy.decorations.HorizontalSeparatorsDecoration;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Ciemek on 12/06/16.
 */
public class PlaylistsActivity extends AppCompatActivity {

    @BindView(R.id.playlists_recycler)
    RecyclerView playlistsRecycler;

    private RecyclerView.LayoutManager layoutManager;
    private PlaylistsAdapter adapter;
    private PlaylistsApi    playlistsApi;
    private Intent          tracksActivityLaunchIntent;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playlists);
        ButterKnife.bind(this);

        SharedPreferences sharedPreferences = getSharedPreferences(getString(R.string.sharedpreferences_global), Context.MODE_PRIVATE);

        playlistsApi = new PlaylistsApi(
                true,
                sharedPreferences.getString(getString(R.string.spotifyAccessToken_value), null),
                true
        );

        buildRecycler();

        playlistsApi.getLoggedUserPlaylists(30, 0, new Callback<SpotifyBaseResponse<PlaylistObject>>() {
            @Override
            public void onResponse(Call<SpotifyBaseResponse<PlaylistObject>> call, Response<SpotifyBaseResponse<PlaylistObject>> response) {
                adapter.updateItems(response.body());
                Snackbar.make(getWindow().getDecorView().getRootView(), "OK: got " + adapter.getItems().size()+ " playlists", Snackbar.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<SpotifyBaseResponse<PlaylistObject>> call, Throwable t) {
                Log.e("Retrofit", "failure :c");
                Snackbar.make(getWindow().getDecorView().getRootView(), "NOK: playlist fetching failed", Snackbar.LENGTH_LONG).show();
            }
        });
    }

    private void buildRecycler() {
        layoutManager = new LinearLayoutManager(this);
        adapter = new PlaylistsAdapter(new RecyclerOnPosClickListener() {
            @Override
            public void onPosClick(View v, int position) {
                //todo: better architecture or something here (maybe send stuff in bundle?)
                tracksActivityLaunchIntent.putExtra("type", "playlist");
                tracksActivityLaunchIntent.putExtra("playlistId", adapter.getItem(position).id);
                startActivity(tracksActivityLaunchIntent);
            }
        });

        playlistsRecycler.setLayoutManager(layoutManager);
        playlistsRecycler.setHasFixedSize(true);
        playlistsRecycler.addItemDecoration(new HorizontalSeparatorsDecoration(this));
        tracksActivityLaunchIntent = new Intent(this, TracksActivity.class);
        playlistsRecycler.setAdapter(adapter);

    }

}
