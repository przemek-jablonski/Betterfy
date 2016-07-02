package com.pszemek.betterfy.backend.services;

import com.pszemek.betterfy.backend.models.SpotifyBaseResponse;
import com.pszemek.betterfy.backend.models.simplified.AlbumSimpleObject;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Ciemek on 01/07/16.
 */
public interface BrowseSpotifyService {

    @GET("browse/new-releases")
    Call<SpotifyBaseResponse<AlbumSimpleObject>> getNewReleasesAlbums(
            @Query("country")   String  countryCode,
            @Query("limit")     Integer limit,
            @Query("offset")    Integer offset
    );

    

}
