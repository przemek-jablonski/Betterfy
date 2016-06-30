package com.pszemek.betterfy.backend.services;

import com.pszemek.betterfy.backend.models.ArtistFullObject;
import com.pszemek.betterfy.backend.models.SpotifyBaseResponse;
import com.pszemek.betterfy.backend.models.TrackFullObject;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Ciemek on 30/06/16.
 */
public interface TopService {

    @GET("me/top/artists")
    Call<SpotifyBaseResponse<ArtistFullObject>> getTopArtists(
            @Query("limit") Integer  limit,
            @Query("offset") Integer offset,
            @Query("time_range") String timeRange
    );

    @GET("me/top/tracks")
    Call<SpotifyBaseResponse<TrackFullObject>> getTopTracks(
            @Query("limit") Integer  limit,
            @Query("offset") Integer offset,
            @Query("time_range") String timeRange
    );
}
