package com.pszemek.betterfy.backend.services;

import com.pszemek.betterfy.backend.models.AlbumFullObject;
import com.pszemek.betterfy.backend.models.SpotifyBaseResponse;
import com.pszemek.betterfy.backend.models.simplified.AddedAtResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Ciemek on 28/06/16.
 */
public interface AlbumsService {

    @GET("me/albums")
    Call<SpotifyBaseResponse<AddedAtResponse<AlbumFullObject>>> getSavedAlbums(
            @Query("limit")  Integer limit,
            @Query("offset") Integer offset,
            @Query("market") String  marketCode);
}
