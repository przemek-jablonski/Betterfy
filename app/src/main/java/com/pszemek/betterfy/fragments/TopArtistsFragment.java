
package com.pszemek.betterfy.fragments;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pszemek.betterfy.R;
import com.pszemek.betterfy.adapters.RecyclerOnPosClickListener;
import com.pszemek.betterfy.adapters.TopAdapter;
import com.pszemek.betterfy.backend.apis.TopApi;
import com.pszemek.betterfy.backend.models.ArtistFullObject;
import com.pszemek.betterfy.backend.models.SpotifyBaseResponse;
import com.pszemek.betterfy.backend.models.TopObject;
import com.pszemek.betterfy.decorations.HorizontalSeparatorsDecoration;
import com.pszemek.betterfy.misc.Utils;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TopArtistsFragment extends Fragment {

    private TopApi      api;
    private TopAdapter  adapter;


    @BindView(R.id.fragment_top_artists_recycler)
    RecyclerView    recycler;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences sharedPreferences = Utils.getSharedPreferences(getContext(), R.string.sharedpreferences_global);

        api = new TopApi(
                true,
                sharedPreferences.getString(getString(R.string.spotifyAccessToken_value), null),
                true
        );
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.from(getContext()).inflate(R.layout.fragment_top_artists, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        ButterKnife.bind(this, getActivity());

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        adapter = new TopAdapter(new RecyclerOnPosClickListener() {
            @Override
            public void onPosClick(View v, int position) {
                Snackbar.make(getView().getRootView(), "click: pos" + position + ".", Snackbar.LENGTH_LONG);
            }
        });

        recycler.addItemDecoration(new HorizontalSeparatorsDecoration(getContext()));
        recycler.setLayoutManager(layoutManager);
        recycler.setAdapter(adapter);

        api.getTopArtists(6, 0, "short_term", new Callback<SpotifyBaseResponse<ArtistFullObject>>() {
                    @Override
                    public void onResponse(Call<SpotifyBaseResponse<ArtistFullObject>> call, Response<SpotifyBaseResponse<ArtistFullObject>> response) {

                        adapter.addItemNoNotify(Utils.createSeparatorFromStrings(
                                getContext(),
                                R.string.top_separator_left_artists,
                                R.string.top_separator_right_short_term));

                        for (int i = 0; i < response.body().items.size(); i++) {
                            adapter.addItemNoNotify(response.body().items.get(i));
                        }

                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onFailure(Call<SpotifyBaseResponse<ArtistFullObject>> call, Throwable t) {
                        Log.e("Retrofit", "failure :c");
                        Snackbar.make(getView().getRootView(), "NOK: playlist TOP (short_term) failed", Snackbar.LENGTH_LONG).show();
                    }
                }
        );

        api.getTopArtists(6, 0, "medium_term", new Callback<SpotifyBaseResponse<ArtistFullObject>>() {
                    @Override
                    public void onResponse(Call<SpotifyBaseResponse<ArtistFullObject>> call, Response<SpotifyBaseResponse<ArtistFullObject>> response) {

                        adapter.addItemNoNotify(Utils.createSeparatorFromStrings(
                                getContext(),
                                R.string.top_separator_left_artists,
                                R.string.top_separator_right_medium_term));

                        for (int i = 0; i < response.body().items.size(); i++) {
                            adapter.addItemNoNotify(response.body().items.get(i));
                        }

                        adapter.notifyDataSetChanged();

                    }

                    @Override
                    public void onFailure(Call<SpotifyBaseResponse<ArtistFullObject>> call, Throwable t) {
                        Log.e("Retrofit", "failure :c");
                        Snackbar.make(getView().getRootView(), "NOK: playlist TOP (medium_term) failed", Snackbar.LENGTH_LONG).show();
                    }
                }
        );

        api.getTopArtists(5, 0, "long_term", new Callback<SpotifyBaseResponse<ArtistFullObject>>() {
                    @Override
                    public void onResponse(Call<SpotifyBaseResponse<ArtistFullObject>> call, Response<SpotifyBaseResponse<ArtistFullObject>> response) {

                        adapter.addItemNoNotify(Utils.createSeparatorFromStrings(
                                getContext(),
                                R.string.top_separator_left_artists,
                                R.string.top_separator_right_long_term));

                        for (int i = 0; i < response.body().items.size(); i++) {
                            adapter.addItemNoNotify(response.body().items.get(i));
                        }

                        adapter.notifyDataSetChanged();

                    }

                    @Override
                    public void onFailure(Call<SpotifyBaseResponse<ArtistFullObject>> call, Throwable t) {
                        Log.e("Retrofit", "failure :c");
                        Snackbar.make(getView().getRootView(), "NOK: playlist TOP (short_term) failed", Snackbar.LENGTH_LONG).show();
                    }
                }
        );

    }

}
