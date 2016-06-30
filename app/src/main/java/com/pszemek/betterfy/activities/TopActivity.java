package com.pszemek.betterfy.activities;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ExpandableListView;

import com.pszemek.betterfy.DoubleStringSeparatorObject;
import com.pszemek.betterfy.R;
import com.pszemek.betterfy.adapters.RecyclerOnPosClickListener;
import com.pszemek.betterfy.adapters.TopAdapter;
import com.pszemek.betterfy.backend.apis.TopApi;
import com.pszemek.betterfy.backend.models.ArtistFullObject;
import com.pszemek.betterfy.backend.models.SpotifyBaseResponse;
import com.pszemek.betterfy.backend.models.TopObject;
import com.pszemek.betterfy.backend.models.TrackFullObject;
import com.pszemek.betterfy.decorations.HorizontalSeparatorsDecoration;
import com.pszemek.betterfy.misc.Utils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TopActivity extends AppCompatActivity {

    private TopApi      topApi;
    private TopAdapter  topAdapter;

    @BindView(R.id.top_recycler)
    RecyclerView topRecycler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top);
        ButterKnife.bind(this);
        buildRecycler();

        SharedPreferences sharedPreferences = getSharedPreferences(getString(R.string.sharedpreferences_global), Context.MODE_PRIVATE);


        topApi = new TopApi(
                true,
                sharedPreferences.getString(getString(R.string.spotifyAccessToken_value), null),
                true
        );

        topApi.getTopArtists(6, 0, "short_term", new Callback<SpotifyBaseResponse<ArtistFullObject>>() {
                    @Override
                    public void onResponse(Call<SpotifyBaseResponse<ArtistFullObject>> call, Response<SpotifyBaseResponse<ArtistFullObject>> response) {

                        topAdapter.addItems(
                                Utils.createSeparatorFromStrings(
                                        getApplicationContext(),
                                        R.string.top_separator_left_artists,
                                        R.string.top_separator_right_short_term),
                                response.body().items
                                );
                    }

                    @Override
                    public void onFailure(Call<SpotifyBaseResponse<ArtistFullObject>> call, Throwable t) {
                        Log.e("Retrofit", "failure :c");
                        Snackbar.make(getWindow().getDecorView().getRootView(), "NOK: playlist TOP (short_term) failed", Snackbar.LENGTH_LONG).show();
                    }
                }
        );

        topApi.getTopArtists(6, 0, "medium_term", new Callback<SpotifyBaseResponse<ArtistFullObject>>() {
                    @Override
                    public void onResponse(Call<SpotifyBaseResponse<ArtistFullObject>> call, Response<SpotifyBaseResponse<ArtistFullObject>> response) {

                        topAdapter.addItems(
                                Utils.createSeparatorFromStrings(
                                        getApplicationContext(),
                                        R.string.top_separator_left_artists,
                                        R.string.top_separator_right_medium_term),
                                response.body().items
                        );
                    }

                    @Override
                    public void onFailure(Call<SpotifyBaseResponse<ArtistFullObject>> call, Throwable t) {
                        Log.e("Retrofit", "failure :c");
                        Snackbar.make(getWindow().getDecorView().getRootView(), "NOK: playlist TOP (medium_term) failed", Snackbar.LENGTH_LONG).show();
                    }
                }
        );

        topApi.getTopArtists(5, 0, "long_term", new Callback<SpotifyBaseResponse<ArtistFullObject>>() {
                    @Override
                    public void onResponse(Call<SpotifyBaseResponse<ArtistFullObject>> call, Response<SpotifyBaseResponse<ArtistFullObject>> response) {

                        topAdapter.addItems(
                                Utils.createSeparatorFromStrings(
                                        getApplicationContext(),
                                        R.string.top_separator_left_artists,
                                        R.string.top_separator_right_long_term),
                                response.body().items
                        );

                    }

                    @Override
                    public void onFailure(Call<SpotifyBaseResponse<ArtistFullObject>> call, Throwable t) {
                        Log.e("Retrofit", "failure :c");
                        Snackbar.make(getWindow().getDecorView().getRootView(), "NOK: playlist TOP (short_term) failed", Snackbar.LENGTH_LONG).show();
                    }
                }
        );

//        topApi.getTopTracks(5, 0, "short_term", new Callback<SpotifyBaseResponse<TrackFullObject>>() {
//                    @Override
//                    public void onResponse(Call<SpotifyBaseResponse<TrackFullObject>> call, Response<SpotifyBaseResponse<TrackFullObject>> response) {
//                        List<TrackFullObject> topObjectList = response.body().items;
//                        for (int i=0; i < topObjectList.size(); ++i){
//                            topAdapter.addItem(topObjectList.get(i));
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(Call<SpotifyBaseResponse<TrackFullObject>> call, Throwable t) {
//                        Log.e("Retrofit", "failure :c");
//                        Snackbar.make(getWindow().getDecorView().getRootView(), "NOK: playlist TOP (short_term) failed", Snackbar.LENGTH_LONG).show();
//                    }
//                }
//        );
//
//        topApi.getTopTracks(5, 0, "long_term", new Callback<SpotifyBaseResponse<TrackFullObject>>() {
//                    @Override
//                    public void onResponse(Call<SpotifyBaseResponse<TrackFullObject>> call, Response<SpotifyBaseResponse<TrackFullObject>> response) {
//                        List<TrackFullObject> topObjectList = response.body().items;
//                        for (int i=0; i < topObjectList.size(); ++i){
//                            topAdapter.addItem(topObjectList.get(i));
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(Call<SpotifyBaseResponse<TrackFullObject>> call, Throwable t) {
//                        Log.e("Retrofit", "failure :c");
//                        Snackbar.make(getWindow().getDecorView().getRootView(), "NOK: playlist TOP (short_term) failed", Snackbar.LENGTH_LONG).show();
//                    }
//                }
//        );

    }


    private void buildRecycler() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        topAdapter = new TopAdapter();

        topAdapter.setRecyclerOnPosClickListener(new RecyclerOnPosClickListener() {
            @Override
            public void onPosClick(View v, int position) {

            }
        });

        topRecycler.addItemDecoration(new HorizontalSeparatorsDecoration(this));
        topRecycler.setLayoutManager(layoutManager);
        topRecycler.setAdapter(topAdapter);
    }





}
