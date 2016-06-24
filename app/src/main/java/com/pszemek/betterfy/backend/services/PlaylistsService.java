package com.pszemek.betterfy.backend.services;

import com.pszemek.betterfy.backend.models.SpotifyBaseResponse;
import com.pszemek.betterfy.backend.models.simplified.Playlist;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Ciemek on 16/06/16.
 */
public interface PlaylistsService {

    @GET("me/playlists")
    Call<SpotifyBaseResponse<Playlist>> getLoggedUserPlaylists(
            @Query("limit")  int limit,
            @Query("offset") int offset
    );

    @GET("users/{user_id}/playlists")
    Call<SpotifyBaseResponse<Playlist>> getUserPlaylists(
            @Path("user_id") String userId,
            @Query("limit")  int limit,
            @Query("offset") int offset
    );

    @GET("/users/{user_id}/playlists/{playlist_id}")
    Call<SpotifyBaseResponse<Playlist>> getUserPlaylist(
            @Path("user_id") String userId,
            @Path("playlist_id") String playlistId,
            @Query("limit")  int limit,
            @Query("offset") int offset
    );

//    @GET("users/{user_id}/playlists/{playlist_id}/tracks")
//    Call<PlaylistsModel> getTracksFromUserPlaylist(
//            @Path("user_id") String userId,
//            @Path("playlist_id") String playlistId,
//            @Query("limit") int limit,
//            @Query("offset") int offset
//    );


    @GET("browse/featured-playlists")
    Call<SpotifyBaseResponse<Playlist>> getFeaturedPlaylists();

    @GET("browse/categories/{id}/playlists")
    Call<SpotifyBaseResponse<Playlist>> getPlaylistsOfCategory();




}
