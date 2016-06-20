package com.pszemek.betterfy.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import com.pszemek.betterfy.R;
import com.pszemek.betterfy.backend.apis.PlaylistsApi;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Ciemek on 20/06/16.
 */
public class TracksActivity extends AppCompatActivity {

//    private String tracksApiUrl;

    private PlaylistsApi playlistsApi;

    @BindView(R.id.tracks_recycler)
    RecyclerView tracksRecycler;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tracks);
        ButterKnife.bind(this);

//        playlistsApi = new PlaylistsApi();


    }


}
