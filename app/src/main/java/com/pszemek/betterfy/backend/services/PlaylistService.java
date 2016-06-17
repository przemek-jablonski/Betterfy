package com.pszemek.betterfy.backend.services;

import com.pszemek.betterfy.backend.models.PlaylistsModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Ciemek on 16/06/16.
 */
public interface PlaylistService {

    @GET("me/playlists")
    Call<PlaylistsModel> getLoggedUserPlaylists(
            @Query("limit") int limit,
            @Query("offset") int offset
//            Callback<PlaylistsModel> callback
    );

    @GET("users/{user_id}/playlists")
    Call<PlaylistsModel> getUserPlaylists();

    @GET("/users/{user_id}/playlists/{playlist_id}")
    Call<PlaylistsModel> getUserPlaylist();

    @GET("users/{user_id}/playlists/{playlist_id}/tracks")
    Call<PlaylistsModel> getTracksFromUserPlaylist();


    @GET("browse/featured-playlists")
    Call<PlaylistsModel> getFeaturedPlaylists();

    @GET("browse/categories/{id}/playlists")
    Call<PlaylistsModel> getPlaylistsOfCategory();




}
