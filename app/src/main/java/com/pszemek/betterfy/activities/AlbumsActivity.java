package com.pszemek.betterfy.activities;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.ProgressBar;

import com.pszemek.betterfy.R;
import com.pszemek.betterfy.adapters.AlbumsGridAdapter;
import com.pszemek.betterfy.adapters.RecyclerOnPosClickListener;
import com.pszemek.betterfy.backend.apis.AlbumsApi;
import com.pszemek.betterfy.backend.models.AlbumFullObject;
import com.pszemek.betterfy.backend.models.SpotifyBaseResponse;
import com.pszemek.betterfy.backend.models.simplified.AddedAtResponse;

import java.io.PrintWriter;
import java.io.StringWriter;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AlbumsActivity extends AppCompatActivity {

    private AlbumsApi     albumsApi;
    private AlbumsGridAdapter adapter;

    @BindView(R.id.artists_recycler)
    RecyclerView artistsRecycler;

    @BindView(R.id.progressBar)
    ProgressBar progressBar;
//    ObjectAnimator progressBarAnimation;

    private void circularProgressBarStart() {
        ObjectAnimator progressBarAnimation = ObjectAnimator.ofInt (progressBar, "progress", 0, 500); // see this max value coming back here, we animale towards that value
        progressBarAnimation.setDuration (5000); //in milliseconds
        progressBarAnimation.setInterpolator (new DecelerateInterpolator());
        progressBarAnimation.start ();
    }

    private void circularProgressBarClear() {
        progressBar.clearAnimation();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_albums);
        ButterKnife.bind(this);
        buildRecycler();

        SharedPreferences sharedPreferences = getSharedPreferences(getString(R.string.sharedpreferences_global), Context.MODE_PRIVATE);

        circularProgressBarStart();

        albumsApi = new AlbumsApi(
                true,
                sharedPreferences.getString(getString(R.string.spotifyAccessToken_value), null),
                true
        );

        albumsApi.getSavedAlbums(50, null, null, new Callback<SpotifyBaseResponse<AddedAtResponse<AlbumFullObject>>>() {
            @Override
            public void onResponse(Call<SpotifyBaseResponse<AddedAtResponse<AlbumFullObject>>> call, Response<SpotifyBaseResponse<AddedAtResponse<AlbumFullObject>>> response) {
                Snackbar.make(
                        getWindow().getDecorView().getRootView(),
                        "SUCCESS: fetched " + response.body().items.size() + " objects.",
                        Snackbar.LENGTH_LONG).show();

                adapter.updateItems(response.body());
                circularProgressBarClear();
            }

            @Override
            public void onFailure(Call<SpotifyBaseResponse<AddedAtResponse<AlbumFullObject>>> call, Throwable t) {
                Snackbar.make(
                        getWindow().getDecorView().getRootView(),
                        "FAILURE: FETCHING ALBUMS FAILED",
                        Snackbar.LENGTH_LONG).show();

                StringWriter sw = new StringWriter();
                PrintWriter pw = new PrintWriter(sw);
                t.printStackTrace(pw);

                Log.e("Retrofit", "AlbumsActivity failure: " + t.getMessage() + " / " + t.getStackTrace());
                Log.e("Retrofit", sw.toString()); // stack trace as a string
            }
        });

        circularProgressBarClear();
    }

    private void buildRecycler() {
        GridLayoutManager layoutManager = new GridLayoutManager(this, 4);
        adapter = new AlbumsGridAdapter(new RecyclerOnPosClickListener() {
            @Override
            public void onPosClick(View v, int position) {
                Snackbar.make(
                        getWindow().getDecorView().getRootView(),
                        "clicked: " + adapter.getItem(position).album.name,
                        Snackbar.LENGTH_LONG).show();
            }
        });

        artistsRecycler.setLayoutManager(layoutManager);
        artistsRecycler.setAdapter(adapter);
    }


}
