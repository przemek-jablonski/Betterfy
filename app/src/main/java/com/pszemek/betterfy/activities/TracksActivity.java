package com.pszemek.betterfy.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.pszemek.betterfy.R;
import com.pszemek.betterfy.adapters.RecyclerOnPosClickListener;
import com.pszemek.betterfy.adapters.TracksAdapter;
import com.pszemek.betterfy.backend.apis.TracksApi;
import com.pszemek.betterfy.backend.models.TrackModel;
import com.pszemek.betterfy.decorations.HorizontalSeparatorsDecoration;
import com.pszemek.betterfy.misc.Utils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Ciemek on 20/06/16.
 */
public class TracksActivity extends AppCompatActivity {

    @BindView(R.id.tracks_recycler)
    RecyclerView tracksRecycler;

    private RecyclerView.LayoutManager layoutManager;
    private TracksAdapter adapter;

    private TracksApi       tracksApi;
    private String          playlistId; //should be like 'itemId' or 'itemBundle'

    private Intent          playActivityLaunchIntent;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tracks);
        ButterKnife.bind(this);

        playlistId = getIntent().getStringExtra("playlistId");
        tracksApi = new TracksApi(true, Utils.getSpotifyAccessToken(this), true);

        buildRecycler();

        tracksApi.getPlaylistTracks(
                Utils.getStringFromSharedPreferences(this, R.string.sharedpreferences_userdata, "userId"),
                getIntent().getStringExtra("playlistId"),
                null,
                null,
                null,
                new Callback<List<TrackModel>>() {
                    @Override
                    public void onResponse(Call<List<TrackModel>> call, Response<List<TrackModel>> response) {
                        int fetchedTracks = response.body().size();
                        adapter.updateItems(response.body());
                        Snackbar.make(getWindow().getDecorView().getRootView(), "OK: got " + fetchedTracks + " tracks", Snackbar.LENGTH_LONG).show();
                    }

                    @Override
                    public void onFailure(Call<List<TrackModel>> call, Throwable t) {
                        Snackbar.make(getWindow().getDecorView().getRootView(), "NOK: playlist fetching failed", Snackbar.LENGTH_LONG).show();
                    }
                }
        );

    }

    private void buildRecycler() {
        layoutManager = new LinearLayoutManager(this);
        adapter = new TracksAdapter();
//        tracksRecycler.setAdapter(adapter);

        tracksRecycler.setLayoutManager(layoutManager);
        tracksRecycler.setHasFixedSize(true);
        tracksRecycler.addItemDecoration(new HorizontalSeparatorsDecoration(this));
//        playActivityLaunchIntent = new Intent(this, PlayActivity.class);
        adapter.setRecyclerOnPosClickListener(new RecyclerOnPosClickListener() {
            @Override
            public void onPosClick(View v, int position) {
                Snackbar.make(
                        getWindow().getDecorView().getRootView(),
                        "clicked: (" + position + ")",
                        Snackbar.LENGTH_LONG
                ).show();
            }
        });
        tracksRecycler.setAdapter(adapter);
    }




}
