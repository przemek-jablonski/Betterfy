package com.pszemek.betterfy.activities;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.pszemek.betterfy.R;
import com.pszemek.betterfy.backend.apis.BrowseSpotifyApi;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewReleasesActivity extends AppCompatActivity {


    private BrowseSpotifyApi browseApi;

    @BindView(R.id.newreleases_recycler)
    RecyclerView newReleasesRecycler;


    //todo: there should be pager 1- worldwide, 2- for country (with selectionbar)
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


    }

    private void buildRecycler() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);

    }
}
