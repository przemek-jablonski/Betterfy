package com.pszemek.betterfy.activities;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.pszemek.betterfy.R;
import com.pszemek.betterfy.adapters.AlbumsSimpleLinearAdapter;
import com.pszemek.betterfy.backend.apis.BrowseSpotifyApi;
import com.pszemek.betterfy.backend.models.SpotifyBaseResponse;
import com.pszemek.betterfy.backend.models.simplified.AlbumSimpleObject;
import com.pszemek.betterfy.decorations.HorizontalSeparatorsDecoration;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewReleasesActivity extends AppCompatActivity {


    private BrowseSpotifyApi            browseApi;
    private AlbumsSimpleLinearAdapter   adapter;

    @BindView(R.id.newreleases_recycler)
    RecyclerView newReleasesRecycler;


    //todo: here should be infinite scolling feature as well
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newreleases);
        ButterKnife.bind(this);

        SharedPreferences sharedPreferences = getSharedPreferences(getString(R.string.sharedpreferences_global), Context.MODE_PRIVATE);

        buildRecycler();

        browseApi = new BrowseSpotifyApi(
                true,
                sharedPreferences.getString(getString(R.string.spotifyAccessToken_value), null),
                true
        );

        browseApi.getNewReleases("PL", 30, 0, new Callback<SpotifyBaseResponse<AlbumSimpleObject>>() {
            @Override
            public void onResponse(Call<SpotifyBaseResponse<AlbumSimpleObject>> call, Response<SpotifyBaseResponse<AlbumSimpleObject>> response) {
                adapter.updateItems(response.body());
                Snackbar.make(getWindow().getDecorView().getRootView(), "OK: worldwide releases fetched", Snackbar.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<SpotifyBaseResponse<AlbumSimpleObject>> call, Throwable t) {
                Snackbar.make(getWindow().getDecorView().getRootView(), "NOK: worldwide releases FAILED", Snackbar.LENGTH_LONG).show();
            }
        });


    }

    private void buildRecycler() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        adapter = new AlbumsSimpleLinearAdapter(null);

        newReleasesRecycler.setLayoutManager(layoutManager);
        newReleasesRecycler.addItemDecoration(new HorizontalSeparatorsDecoration(this));
        newReleasesRecycler.setHasFixedSize(true);
        newReleasesRecycler.setAdapter(adapter);



    }
}
