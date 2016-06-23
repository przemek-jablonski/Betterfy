package com.pszemek.betterfy.backend.services;

import android.support.annotation.Nullable;

import com.pszemek.betterfy.backend.models.TrackModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Ciemek on 20/06/16.
 */
public interface TracksService {

    enum TimeRange {
        long_term,      //calculation scope of several (~4) years
        medium_term,    //calculation scope of last 4 months
        short_term      //calculation scope of last 4 weeks
    }

    @GET("tracks/{track_id}")
    Call<TrackModel> getTrack(
            @Path("track_id")   String  trackId,
            @Query("market")    String  marketCode
    );


//    @GET("tracks")
//    Call<List<TrackModel>> getTracks(
//            @Query("tracks_id")  String[] tracksId,  //todo: propably (String[] tracksId) is wrong, fixit!
//            @Query("market")    String marketCode
//    );


//    @GET("audio-features/{track_id}")
//    Call<AudioFeaturesModel> getTrackAudioFeatures(
//            @Path("track_id")  String trackId
//    );


//    @GET("audio-features")
//    Call<List<AudioFeaturesModel>> getTracksAudioFeatures(
//            @Query("ids")       String[] tracksId   //todo: propably (String[] tracksId) is wrong, fixit!
//    );


    @GET("albums/{album_id}/tracks")
    Call<List<TrackModel>> getAlbumTracks(
            @Path("album_id")   String  albumId,
            @Query("limit")     Integer limit,
            @Query("offset")    Integer offset,
            @Query("market")    String  marketCode
    );


    @GET("artists/{artist_id}/top-tracks")
    Call<List<TrackModel>> getArtistTracksTop(
            @Path("artist_id")  String  artistId,
            @Query("country")   String  countryCode
    );


    @GET("artists/{artist_id}/related-artists")
    Call<List<TrackModel>> getArtistTracksRelated(
            @Path("artist_id")  String  artistId
    );


    @GET("users/{user_id}/playlists/{playlist_id}/tracks")
    Call<List<TrackModel>> getPlaylistTracks(
            @Path("user_id")    String  userId,
            @Path("playlist_id") String playlistId,
            @Query("market")    String  marketCode,
            /*@Query("fields")    String[] fields,*/     //todo: figure out this FIELD shit
            @Query("limit")     Integer limit,
            @Query("offset")    Integer offset
    );


    @GET("me/tracks")
    Call<List<TrackModel>> getLoggedUserSavedTracks(
            @Query("market")    String  marketCode,
            @Query("limit")     Integer limit,
            @Query("offset")    Integer offset
    );

    @GET("me/top/tracks")
    Call<List<TrackModel>> getLoggedUserTopTracks(
            @Query("limit")     Integer limit,
            @Query("offset")    String  offset,
            @Query("time_range") TimeRange timeRange
    );
}
